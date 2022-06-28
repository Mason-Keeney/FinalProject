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
import { faMagnifyingGlass, faToolbox } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { ToolService } from 'src/app/services/tool.service';
import { Tool } from 'src/app/models/tool';

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
  search: string = '';
  faMagnifyingGlass = faMagnifyingGlass;
  faToolbox = faToolbox;
  selected: Project | null = null;
  newProject: Project = new Project();
  startDateString: string = '';
  estimatedEndDateString: string = '';
  user: User = new User;
  updateChecker: Project = new Project;
  toolListFull: Tool[] = [];
  toolList: Tool[] = [];
  participant: Participant = new Participant;
  participantProject: Project | null = null;
  inspect: Project | null = null;

  constructor(
    private projectServ: ProjectService,
    private userServ: UserService,
    private router: Router,
    private authService: AuthService,
    private toolService: ToolService
    ) {}

    @ViewChild(ParticipantComponent, { static: false })
    participantComponent!: ParticipantComponent;

    @ViewChild(InspectProjectComponent, { static: false })
    inspectProjectComponent!: InspectProjectComponent;

    ngOnInit(): void {
      this.toolIndex();
      this.authenticateUser();
      this.index();
      this.indexAll();
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

    projectContains(userID: number | null, projectID: number | null): boolean{
        let projectRunner = new Project;
        let userRunner = new User;
        let projectvar = false;
        let contains = false;
        if(projectID) {

      }
      if(this.user.id === userID && projectvar){
        if(projectRunner.particpants) {
        projectRunner.particpants.forEach(Participant => {
          if(Participant.user?.id === userID){
            contains = true;
          }
        });
      }
      }
      return contains;
    }

    toggleInspect(project: Project){
      if(this.inspect != project){
        this.inspect = project;
      }
      else {
        this.inspect = null;
      }
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

    setUpdate(project: Project): void {
      if(this.updateChecker != project) {
        this.updateChecker = project;
      } else {
        this.updateChecker = new Project;
      }

    }

    reload() {
      this.project.id = 1;
      this.show(this.project);
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
      },
      error: (fail) => {
        console.error('ProjectComponent.adding: error creating project');
        console.error(fail);
      },
    });
  }

  updateProject(id: number | null, project: Project): void {
    this.projectServ.update(id, project).subscribe({
      next: (result) => {
        this.project = result;
        window.alert('A project was updated!');
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
      },
      error: (nojoy) => {
        console.error('ProjectComponent.deleting: error deleting project');
        console.error(nojoy);
        this.reload();
      },
    });
  }

  toolIndex() {
    this.toolService.indexAll().subscribe({
      next: (result) => {
        this.toolListFull = result;
      },
      error: (nojoy) => {
       console.log('Tool.index(): error retrieving Tools:');
       console.log(nojoy);
      },
    });
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
