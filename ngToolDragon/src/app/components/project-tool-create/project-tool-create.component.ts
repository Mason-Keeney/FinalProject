import { ProjectTool } from './../../models/project-tool';
import { ProjectToolService } from './../../services/project-tool.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, Input, OnInit } from '@angular/core';
import { Tool } from 'src/app/models/tool';
import { User } from 'src/app/models/user';
import { Project } from 'src/app/models/project';
import { faHammer } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-project-tool-create',
  templateUrl: './project-tool-create.component.html',
  styleUrls: ['./project-tool-create.component.css']
})
export class ProjectToolCreateComponent implements OnInit {

  // Inherited Fields
  @Input() inheritedTool: any;
  @Input() inheritedProject: any;


  //Fields
  tool: Tool = new Tool();
  project: Project = new Project();
  comment: string = "";
  user: User = new User();
  projectTool: ProjectTool = new ProjectTool();

  //fa-icons
  faHammer = faHammer;

  constructor(
    private authService: AuthService,
    private projectToolService: ProjectToolService
  ) { }

  ngOnInit(): void {
    this.tool = this.inheritedTool;
    this.project = this.inheritedProject;
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

  buildProjectTool(comment: string | null){
    this.projectTool.projectComment = comment;
    this.createProjectTool(this.project.id, this.tool.id);
  }

  createProjectTool(pid: number | null, tid: number | null){
    this.projectToolService.create(this.projectTool, pid, tid).subscribe({
      next: (result) => {
        this.projectTool = result;
      },
      error: (problem) => {
        console.log("ProjectToolCreateHttpComponent Error: unable to Create ProjectTool");
        console.log(problem);
      }
    })
  }

}
