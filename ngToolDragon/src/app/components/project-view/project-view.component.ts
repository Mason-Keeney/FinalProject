import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { Project } from 'src/app/models/project';
import { User } from 'src/app/models/user';
import { ProjectService } from 'src/app/services/project.service';
import { UserService } from 'src/app/services/user.service';
import { environment } from 'src/environments/environment';
import { Tool } from 'src/app/models/tool';
import { ToolService } from 'src/app/services/tool.service';

@Component({
  selector: 'app-project-view',
  templateUrl: './project-view.component.html',
  styleUrls: ['./project-view.component.css']
})
export class ProjectViewComponent implements OnInit {
  private url = environment.baseUrl + 'api/projectView';
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

  reload() {
    this.project.id = 1;
    this.show(this.project);
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
    console.log(project);
    project.startDate = new Date(this.startDateString);
    project.estimatedEndDate = new Date(this.estimatedEndDateString);
    console.log(project);

    this.projectServ.create(project).subscribe({
      next: (result) => {
        this.project = result;
        window.alert('A project was created!');
      },
      error: (fail) => {
        console.error('ProjectViewComponent.adding: error creating project');
        console.error(fail);
      },
    });
  }

  updateProject(id: number | null, project: Project): void {
    this.projectServ.update(id, project).subscribe({
      next: (result) => {
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
        window.alert(name + "'s project was deleted");
      },
      error: (nojoy) => {
        console.error('ProjectComponent.deleting: error deleting project');
        console.error(nojoy);
        this.reload();
      },
    });
  }



}
