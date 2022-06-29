import { Project } from 'src/app/models/project';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private url = environment.baseUrl + 'api/projects';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return options;
  }

  index(): Observable<Project[]> {
    return this.http.get<Project[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.index(): error retrieving Projects:' + err)
        );
      })
    );
  }

  indexAll(): Observable<Project[]> {
    return this.http.get<Project[]>(this.url + "/all").pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('Project.index(): error retrieving Projects:' + err)
      );
    })
  );
}

  show(id: number | null): Observable<Project> {
    return this.http.get<Project>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.show(): error retrieving Project:' + err)
        );
      })
    );
  }

  create(project: Project): Observable<Project> {
    console.log(project);
    return this.http.post<Project>(this.url, project, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.create(): error creating Project:' + err)
        );
      })
    );
  }

  update(id: number | null, project: Project): Observable<Project> {
    return this.http.put<Project>(this.url + '/' + id, project, this.getHttpOptions()).pipe(
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
    return this.http.put<Project>(this.url + '/' + id, project, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.update(): error retrieving Project:' + err)
        );
      })
    );
  }

  destroy(id: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Project.destroy(): error retrieving Project:' + err)
        );
      })
    );
  }

  // searchProjectByKeyowrd(keyowrd: string) Observable<Project> {
  //   return this.http.delete<Project[]>(this.url + '/search/' + keyword).pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError(
  //         () => new Error('Project.searchProjectByKeyword(): error retrieving Project:' + err)
  //       );
  //     })
  //   );
  // }

}
