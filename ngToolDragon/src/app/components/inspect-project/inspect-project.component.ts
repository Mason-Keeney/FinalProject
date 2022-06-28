import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { faCheck, faCircleXmark } from '@fortawesome/free-solid-svg-icons';
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

@Component({
  selector: 'app-inspect-project',
  templateUrl: './inspect-project.component.html',
  styleUrls: ['./inspect-project.component.css']
})
export class InspectProjectComponent implements OnInit {
  user: User = new User;
  projects: Project[] = [];
  toolListFull: Tool[] = [];
  toolList: Tool[] = [];
  tool: Tool = new Tool;

  // fa-icons
  faCheck = faCheck;
  faCircleX = faCircleXmark;


  @Input() project: any;

  constructor(
    private projectServ: ProjectService,
    private userServ: UserService,
    private toolServ: ToolService,
    private authService: AuthService,
    private toolService: ToolService,
  ) { }

  @ViewChild(ParticipantComponent, { static: false })
    participantComponent!: ParticipantComponent;

  @ViewChild(ToolComponent, { static: false })
  toolComponent!: ToolComponent;

  // @ViewChild(MaterialComponent, { static: false })
  // materialComponent!: MaterialComponent;

  ngOnInit(): void {
    this.toolIndex();
    this.authenticateUser();
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



}
