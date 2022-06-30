import { ProjectPresentPipe } from './../../pipes/project-present.pipe';
import { ParticipantService } from './../../services/participant.service';
import { InspectProjectComponent } from './../inspect-project/inspect-project.component';
import { ParticipantComponent } from './../participant/participant.component';
import { Project } from './../../models/project';
import { Participant } from './../../models/participant';
import { UserService } from './../../services/user.service';
import { Category } from './../../models/category';
import { ProjectService } from './../../services/project.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { faListCheck, faMagnifyingGlass, faToolbox, faPlusCircle, faEye, faArrowRotateLeft, faCheck, faCircleXmark } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { ToolService } from 'src/app/services/tool.service';
import { Tool } from 'src/app/models/tool';
import { ProjectToolComponent } from '../project-tool/project-tool.component';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css'],
})
export class ProjectComponent implements OnInit {
  private url = environment.baseUrl + 'api/project';

  projects: Project[] = [];
  fullProjects: Project[] = [];
  project: Project = new Project;
  newProject: Project = new Project();
  selected: Project | null = null;
  updateChecker: Project = new Project;
  participantProject: Project | null = null;
  inspect: Project | null = null;
  updateStartDate: string = "";
  updateEndDate: string = "";

  allParticipantsList: Participant[] = [];
  participant: Participant = new Participant;

  search: string = '';
  startDateString: string = '';
  estimatedEndDateString: string = '';
  user: User = new User;

  toolListFull: Tool[] = [];
  toolList: Tool[] = [];

  editingProject: Boolean = false;


  //FA-ICONS
  faMagnifyingGlass = faMagnifyingGlass;
  faToolbox = faToolbox;
  faListCheck = faListCheck;
  faPlusCircle = faPlusCircle;
  faEye = faEye;
  faArrowRotateLeft = faArrowRotateLeft;
  faCheck = faCheck;
  faCircleXmark = faCircleXmark;

  @ViewChild(ProjectToolComponent, { static: false })
  projectToolComponent!: ProjectToolComponent;


  constructor(
    private projectServ: ProjectService,
    private userServ: UserService,
    private router: Router,
    private authService: AuthService,
    private participantService: ParticipantService,
    private projectPresentPipe: ProjectPresentPipe
    ) {}

    @ViewChild(ParticipantComponent, { static: false })
    participantComponent!: ParticipantComponent;

    @ViewChild(InspectProjectComponent, { static: false })
    inspectProjectComponent!: InspectProjectComponent;

    ngOnInit(): void {
      this.authenticateUser();
      this.index();
      this.indexAll();
      this.indexParticipants();
    }

    authenticateUser(){
      this.authService.authenticateUser().subscribe({
        next: (result) =>{
          this.user = result;
        },
        error: (problem) => {
          console.log(problem);
        }
      })
    }

    toggleEditproject(){
      this.editingProject = !this.editingProject;
      if(this.editingProject){
      } else {
        this.projectToolComponent.ProjectTool = new Project();
      }
    }

    checkLogin(): boolean{
      return this.authService.checkLogin();
    }

    projectContains(project: Project): boolean{
    let show: boolean = false;
    if(project.owner?.id === this.user.id){
        show = true;
    }

    if(!show){
      project.particpants = this.projectPresentPipe.transform(this.allParticipantsList, project);
      if(project.particpants) {
        for (let index = 0; index < project.particpants.length; index++) {
          let user = project.particpants[index].user;
          if(user?.id == this.user.id) {
            show = true;
          }
        }
      }
    }
    return show;
    }


   viewParticipant(project: Project){
    let participant: Participant = new Participant;
    this.participantService.show(project.id, this.user.id).subscribe({
      next: (result) => {
        participant = result;
        console.log(participant);
      },
      error: (problem) => {
        console.log("ProjectHttpComponent Error: unable to find Participant");
        console.log(problem);
      }
    })

   }

    toggleInspect(project: Project){
      if(this.inspect != project){
        this.inspect = project;
      }
      else {
        this.inspect = null;
      }
    }

    indexParticipants(){
      this.participantService.index().subscribe({
        next:(result) => {
         this.allParticipantsList = result;
        },
        error: (problem) => {
          console.log("ProjectHttpComponent Error: unable to populate ParticipantList")
          console.log(problem);
        }
      })
    }

    addPartRequest(project:Project|null): void {
      if(!this.participantProject){
        this.participant.project = project;
        this.participant.user = this.user;
        this.participantProject = project;
      } else {
        this.participantProject = null;
      }
    }

    // setUpdate(project: Project): void {
    //   if(this.updateChecker != project) {
    //     this.updateChecker = project;
    //   } else {
    //     this.updateChecker = new Project;
    //   }

    // }

    reload() {
      this.index();
      this.indexAll();
      this.indexParticipants();
    }

  setSelected(project: Project) {
    if (this.selected != project) {
      this.selected = project;
    } else {
      this.selected = null;
    }
  }

  index() {
    this.projectServ.index().subscribe({
      next: (result) => {
        this.projects = result;
      },
      error: (nojoy) => {
        console.log('Project.index(): error retrieving Projects:');
        console.log(nojoy);
      },
    });
  }
  indexAll() {
    this.projectServ.indexAll().subscribe({
      next: (result) => {
        this.fullProjects = result;
      },
      error: (nojoy) => {
        console.log('Project.index(): error retrieving Projects:');
        console.log(nojoy);
      },
    });
  }

  show(project: Project): void {
    this.projectServ.show(project.id).subscribe({
      next: (result) => {
        this.project = result;
      },
      error: (nojoy) => {
        console.log('Project.show(): error retrieving Project:');
        console.log(nojoy);
      },
    });
  }


  addProject(project: Project): void {
    this.projectServ.create(project).subscribe({
      next: (result) => {
        this.project = result;
        window.alert('A project was created!');
        this.reload();
      },
      error: (fail) => {
        console.error('ProjectComponent.adding: error creating project');
        console.error(fail);
      },
    });
  }

  updateProject(id: number | null, project: Project): void {
    let startDate: Date = new Date(this.updateStartDate);
    let endDate: Date = new Date(this.updateEndDate);
    project.startDate = startDate;
    project.estimatedEndDate = endDate;
    console.log(project);

    this.projectServ.update(id, project).subscribe({
      next: (result) => {
        this.project = result;
        window.alert('A project was updated!');
        this.reload();
      },
      error: (nojoy) => {
        console.error('ProjectComponent.adding: error updating project');
        console.error(nojoy);
      },
    });
  }

  deleteProject(project: Project): void {
    this.projectServ.destroy(project.id).subscribe({
      next: () => {
        window.alert("project was deleted");
        this.reload();
      },
      error: (nojoy) => {
        console.error('ProjectComponent.deleting: error deleting project');
        console.error(nojoy);
        this.reload();
      },
    });
  }

  getNumProject() {
    return this.projects.length;
  }
  projectIndex() {
    this.projectServ.indexAll().subscribe({
      next: (result) => {
        this.fullProjects = result;
      },
      error: (nojoy) => {
       console.log('Tool.index(): error retrieving Tools:');
       console.log(nojoy);
      },
    });
  }

  checkProjectLevel() {
    let numOfTodos = this.getNumProject();
    if (numOfTodos >= 10) {
      return 'badge bg-danger';
    } else if (numOfTodos >= 5) {
      return 'badge bg-warning';
    } else {
      return 'badge bg-success';
    }
  }

  setCompletedClass(project: Project): string{
    if(project.completed){
      return "btn btn-success disabled";
    } else {
      return "btn btn-warning disabled";
    }
  }

  // loadProject(): void {
  //   this.projectServ.index().subscribe({
  //     next: (project) => {
  //       this.projects = project;
  //     },
  //     error: (problem) => {
  //       console.error(
  //         'ProjectListHttpComponent.loadProject(): error loading projects:'
  //       );
  //       console.error(problem);
  //     },
  //   });
  // }

}
