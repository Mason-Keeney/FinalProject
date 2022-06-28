import { ProjectTool } from './../../models/project-tool';
import { Component, Input, OnInit } from '@angular/core';
import { Project } from 'src/app/models/project';
import { Tool } from 'src/app/models/tool';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ProjectService } from 'src/app/services/project.service';
import { ToolService } from 'src/app/services/tool.service';
import { UserService } from 'src/app/services/user.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-project-tool',
  templateUrl: './project-tool.component.html',
  styleUrls: ['./project-tool.component.css']
})


export class ProjectToolComponent implements OnInit {
  // private url = environment.baseUrl + 'api/projectTool';
  @Input() ProjectTool: any;
  projects: Project[] = [];
  project: Project = new Project;
  user: User = new User;
  newProject: Project = new Project();
  startDateString: string = '';
  estimatedEndDateString: string = '';
  search: string = '';
  toolListFull: Tool[] = [];
  toolList: Tool[] = [];



  constructor(
    private projectServ: ProjectService,
    private userServ: UserService,
    private authService: AuthService,
    private toolService: ToolService,
  ) { }



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

}
