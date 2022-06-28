import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProjectMaterial } from '../models/project-material';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProjectMaterialService {
  private url = environment.baseUrl + 'api/materials';

  constructor(private http: HttpClient,
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

  index(): Observable<ProjectMaterial[]> {
    return this.http.get<ProjectMaterial[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ProjectMaterial.index(): error retrieving ProjectMaterials:' + err)
        );
      })
    );
  }

  show(id: number | null): Observable<ProjectMaterial> {
    return this.http.get<ProjectMaterial>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ProjectMaterial.show(): error retrieving ProjectMaterial:' + err)
        );
      })
    );
  }

  create(projectMaterial: ProjectMaterial): Observable<ProjectMaterial> {
    return this.http.post<ProjectMaterial>(this.url, projectMaterial, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ProjectMaterial.create(): error creating ProjectMaterial:' + err)
        );
      })
    );
  }

  update(id: number | null, projectMaterial: ProjectMaterial): Observable<ProjectMaterial> {
    return this.http.put<ProjectMaterial>(this.url + '/' + id, projectMaterial, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ProjectMaterial.update(): error updating ProjectMaterial:' + err)
        );
      })
    );
  }

  destroy(id: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ProjectMaterial.destroy(): error destroying Material:' + err)
        );
      })
    );
  }
}
