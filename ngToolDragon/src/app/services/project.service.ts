import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Project } from '../models/project';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private url = environment.baseUrl + 'api/adresses';

  constructor(private http: HttpClient) {}

  index(): Observable<Project[]> {
    return this.http.get<Project[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.index(): error retrieving Projects:' + err)
        );
      })
    );
  }

  show(id: number | null): Observable<Project> {
    return this.http.get<Project>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.show(): error retrieving Project:' + err)
        );
      })
    );
  }

  create(project: Project): Observable<Project> {
    return this.http.post<Project>(this.url, project).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.create(): error retrieving Project:' + err)
        );
      })
    );
  }

  update(id: number | null, project: Project): Observable<Project> {
    return this.http.put<Project>(this.url + '/' + id, project).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.update(): error retrieving Project:' + err)
        );
      })
    );
  }
  deactivate(id: number | null, project: Project): Observable<Project> {
    project.active = false;
    return this.http.put<Project>(this.url + '/' + id, project).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.update(): error retrieving Project:' + err)
        );
      })
    );
  }

  destroy(id: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.destroy(): error retrieving Project:' + err)
        );
      })
    );
  }
}
