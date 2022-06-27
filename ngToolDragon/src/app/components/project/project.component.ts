import { UserService } from './../../services/user.service';
import { Category } from './../../models/category';
import { ProjectService } from './../../services/project.service';
import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/models/project';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css'],
})
export class ProjectComponent implements OnInit {
  projects: Project[] = [];
  selected: Project | null = null;
  newProject: Project = new Project();

  constructor(
    private projectServ: ProjectService,
    private userServ: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProject();
  }

  index() {
    this.loadProject;
  }

  selectedType: String = 'all';

  loadProject(): void {
    this.projectServ.index().subscribe({
      next: (project) => {
        this.projects = project;
      },
      error: (problem) => {
        console.error(
          'ProjectListHttpComponent.loadProject(): error loading projects:'
        );
        console.error(problem);
      },
    });
  }

  // setEditProject() {
  //   this.editProject = Object.assign({}, this.selected);
  // }

  displayProject(project: Project) {
    this.selected = project;
  }

  displayTable() {
    this.selected = null;
  }

  getNumProjects() {
    return this.projects.length;
  }

  checkProjectLevel() {
    let numOfProjects = this.getNumProjects();
    if (numOfProjects >= 10) {
      return 'badge bg-danger';
    } else if (numOfProjects >= 5) {
      return 'badge bg-warning';
    } else {
      return 'badge bg-success';
    }
  }

  reload(): void {
    this.projectServ.index().subscribe({
      next: (projects) => {
        this.projects = projects;
      },
      error: (fail) => {
        console.error('TodoListComponent.reload: error');
        console.error(fail);
      },
    });
  }

  addProject(project: Project) {
    this.projectServ.create(project).subscribe({
      next: (result) => {
        this.newProject = new Project();
        this.loadProject();
      },
      error: (fail) => {
        console.error('ProjectComponent.adding: error creating project');
        console.error(fail);
      },
    });
  }


  updateProject(
    id: number | null,
    project: Project,
    setSelected: boolean = true
  ) {
    this.projectServ.update(id, project).subscribe({
      next: (updated) => {
        this.reload();
        if (setSelected) {
          this.selected = updated;
        }
        this.selected = updated;
      },
      error: (nojoy) => {
        console.error('ProjectComponent.adding: error updating project');
        console.error(nojoy);
      },
    });
  }

  deleteProject(id: number): void {
    this.projectServ.destroy(id).subscribe({
      next: () => {
        this.reload();
      },
      error: (nojoy) => {
        console.error('ProjectComponent.deleting: error deleting project');
        console.error(nojoy);
      },
    });
  }

  types = [
    'all',
     'Landscaping my yard',
      'example'
    ];





}
