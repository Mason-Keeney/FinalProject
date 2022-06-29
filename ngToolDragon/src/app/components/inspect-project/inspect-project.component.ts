import { ParticipantService } from './../../services/participant.service';
import { ProjectPresentPipe } from './../../pipes/project-present.pipe';
import { ProjectToolService } from './../../services/project-tool.service';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { faCalendarCheck, faCheck, faPlusCircle, faDragon, faCircleXmark, faArrowRotateLeft, faA, faClock } from '@fortawesome/free-solid-svg-icons';
import { Project } from 'src/app/models/project';
import { Tool } from 'src/app/models/tool';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ProjectService } from 'src/app/services/project.service';
import { ToolService } from 'src/app/services/tool.service';
import { UserService } from 'src/app/services/user.service';
import { ParticipantComponent } from '../participant/participant.component';
import { ProjectToolComponent } from '../project-tool/project-tool.component';
import { ToolComponent } from '../tool/tool.component';
import { ProjectTool } from 'src/app/models/project-tool';
import { Participant } from 'src/app/models/participant';

@Component({
  selector: 'app-inspect-project',
  templateUrl: './inspect-project.component.html',
  styleUrls: ['./inspect-project.component.css']
})
export class InspectProjectComponent implements OnInit {

  participantsList: Participant[] = [];
  projectToolList: ProjectTool[] = [];
  selectedParticipant: Participant = new Participant();

  user: User = new User;
  projects: Project[] = [];
  project: Project = new Project();

  toolListFull: Tool[] = [];
  toolList: Tool[] = [];
  tool: Tool = new Tool;
  toolProject: Tool | null = null;

  selectTool: boolean = false;
  createToolRequest: boolean = false;
  viewParticipantDetails: boolean = false;

  // fa-icons
  faCheck = faCheck;
  faCalendarCheck = faCalendarCheck;
  faPlusCircle = faPlusCircle;
  faDragon = faDragon;
  faCircleXmark = faCircleXmark;
  faArrowRotateLeft = faArrowRotateLeft;
  faClock = faClock;


  @Input() inheritedProject: any;

  constructor(
    private projectServ: ProjectService,
    private userServ: UserService,
    private toolServ: ToolService,
    private authService: AuthService,
    private toolService: ToolService,
    private projectToolService: ProjectToolService,
    private projectPresentPipe: ProjectPresentPipe,
    private participantService: ParticipantService
  ) { }

  @ViewChild(ParticipantComponent, { static: false })
    participantComponent!: ParticipantComponent;

  @ViewChild(ToolComponent, { static: false })
  toolComponent!: ToolComponent;

  // @ViewChild(MaterialComponent, { static: false })
  // materialComponent!: MaterialComponent;

  ngOnInit(): void {
    this.project = this.inheritedProject;
    this.toolIndex();
    this.authenticateUser();
    this.indexProjectTool();
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

  reload(){
    console.log("reached reload");

    this.toolIndex();
    this.authenticateUser();
    this.indexProjectTool();
    this.indexParticipants();
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

  indexProjectTool(){
    this.projectToolService.index().subscribe({
      next: (result) => {
        this.projectToolList = result;
        this.project.projectTools = this.projectPresentPipe.transform(this.projectToolList, this.project)
      },
      error: (problem) => {
        console.log("InspectProjectHttpComponent Error: unable to populate ProjectTools");
        console.log(problem);
      }
    })
  }

  indexParticipants(){
    this.participantService.index().subscribe({
      next: (result) => {
        this.participantsList = result;
        this.project.particpants = this.projectPresentPipe.transform(this.participantsList, this.project)
      },
      error: (problem) => {
        console.log("InspectProjectHttpComponent Error: unable to populate ParticipantsList")
        console.log(problem);

      }
    })
  }

  show(tool: Tool): void {
    this.toolServ.show(tool.id).subscribe({
      next: (result) => {
        this.tool = result;
      },
      error: (nojoy) => {
        console.log('Tool.show(): error retrieving Tool:');
        console.log(nojoy);
      },
    });
  }

  setClass(project: Project): string {
    if (project.completed) {
      return "btn btn-success";
    } else {
      return "btn btn-warning";
    }
  }

  addToolRequest(tool: Tool): void {
    if(this.tool != tool){
      this.tool = tool;
      this.createToolRequest = true;
    } else {
      this.tool = new Tool();
      this.createToolRequest = false;
    }
  }

  viewParticipant(participant: Participant){
    console.log(participant)
    this.selectedParticipant = participant;
    this.viewParticipantDetails = !this.viewParticipantDetails;
    this.reload();
  }

  declineParticipant(participant: Participant){
    let tempProject = participant.project;
    let tempUser = participant.user;
    if(tempProject && tempUser){
      this.participantService.destroy(tempProject?.id, tempUser.id).subscribe({
        next: () => {
          this.reload();
        },
        error: (problem) => {
          console.log("InspectProjectHttpComponent Error: unable to destroy Participant");
          console.log(problem);
        }
      })
    }
  }

  removeProjectTool(projectTool: ProjectTool){
    let tempProject = projectTool.project;
    let tempTool = projectTool.tool;
    if(tempProject && tempTool){
      this.projectToolService.destroy(tempProject.id, tempTool.id).subscribe({
       next: () => {
        this.reload();
       },
       error: (problem) => {
        console.log("InspectProjectHttpComponent Error: unable to remove ProjectTool");
        console.log(problem);
       }
      })
    }
  }


}
