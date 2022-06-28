import { Project } from './../models/project';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Participant } from '../models/participant';
import { ProjectTool } from '../models/project-tool';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProjectToolService {
  private url = environment.baseUrl + 'api/projecttools';

  constructor(
    private http: HttpClient,
    private authService: AuthService) {}

    getHttpOptions() {
      let options = {
        headers: new HttpHeaders({
          Authorization: 'Basic ' + this.authService.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
        }),
      };
      return options;
    }

  index(): Observable<ProjectTool[]> {
    return this.http.get<ProjectTool[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('ProjectTool.index(): error retrieving ProjectTools:' + err)
        );
      })
    );
  }

  show(pid: number | null, tid: null | null): Observable<ProjectTool> {
    return this.http.get<ProjectTool>(this.url + '/' + pid + '/' + tid, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('ProjectTool.show(): error retrieving ProjectTool:' + err)
        );
      })
    );
  }

  create(projectTool: ProjectTool): Observable<ProjectTool> {
    return this.http.post<ProjectTool>(this.url, projectTool, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('ProjectTool.create(): error creating ProjectTool:' + err)
        );
      })
    );
  }

  update(pid: number | null, tid: number | null, projectTool: ProjectTool): Observable<ProjectTool> {
    return this.http.put<ProjectTool>(this.url + '/' + pid + '/' + tid, projectTool, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('ProjectTool.update(): error updating ProjectTool:' + err)
        );
      })
    );
  }

  destroy(pid: number | null, tid: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + pid + '/' + tid, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'ProjectTool.destroy(): error destroying ProjectTool:' + err
            )
        );
      })
    );
  }
}
