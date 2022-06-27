import { UserService } from './../../services/user.service';
import { Category } from './../../models/category';
import { ProjectService } from './../../services/project.service';
import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/models/project';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css'],
})
export class ProjectComponent implements OnInit {
  private url = environment.baseUrl + 'api/project';
  projects: Project[] = [];
  project: Project = new Project;

  constructor(
    private projectServ: ProjectService,
    private userServ: UserService,
    ) {}

    ngOnInit(): void {
      this.index();
    }

    reload() {
      this.project.id = 1;
      this.show(this.project);
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
