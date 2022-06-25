import { ProjectService } from './../../services/project.service';
import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/models/project';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
  title = 'ngProject';
  newProject: Project = new Project();
  selected: null | Project = null;
  editProject: null | Project = null;
  showComplete: boolean = false;

  projects: Project[] = [];

  constructor(private projectServ: ProjectService, private route: ActivatedRoute, private router: Router, private datePipe: DatePipe) { }

  ngOnInit(): void {
  }

  setEditProject() {
    this.editProject = Object.assign({}, this.selected);
  }

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

  create(project: Project): Observable<Project> {
    project.completed = false;
    project.description = '';

    return this.http.post<Project>(this.url, project, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ProjectService.create(): error creating project: ' + err)
        );
      })
    );
  }

  update(project: Project): Observable<Project> {
    if (project.completed) {
      project.completeDate = this.datePipe.transform(Date.now(), 'shortDate');
    } else {
      project.completeDate = '';
    }

    return this.http.put<Project>(this.url + '/' + project.id, project, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ProjectService.update(): error updating project: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ProjectService.delete(): error deleting project: ' + err)
        );
      })
    );
  }

}
