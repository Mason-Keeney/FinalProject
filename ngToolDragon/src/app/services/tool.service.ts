import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { throwError } from 'rxjs/internal/observable/throwError';
import { environment } from 'src/environments/environment';
import { Tool } from '../models/tool';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ToolService {
  private url = environment.baseUrl + 'api/tools';

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

  index(): Observable<Tool[]> {
    return this.http.get<Tool[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.index(): error retrieving Tools:' + err)
        );
      })
    );
  }

  indexAll(): Observable<Tool[]> {
    return this.http.get<Tool[]>(this.url + "/all", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.index(): error retrieving Tools:' + err)
        );
      })
    );
  }

  show(id: number | null): Observable<Tool> {
    return this.http.get<Tool>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.show(): error retrieving Tool:' + err)
        );
      })
    );
  }

  create(tool: Tool): Observable<Tool> {
    return this.http.post<Tool>(this.url, tool, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.create(): error retrieving Tool:' + err)
        );
      })
    );
  }

  update(id: number | null, tool: Tool): Observable<Tool> {
    return this.http.put<Tool>(this.url + '/' + id, tool, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.update(): error retrieving Tool:' + err)
        );
      })
    );
  }
  deactivate(id: number | null, tool: Tool): Observable<Tool> {
    tool.active = false;
    return this.http.put<Tool>(this.url + '/' + id, tool, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.update(): error retrieving Tool:' + err)
        );
      })
    );
  }

  destroy(id: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.destroy(): error retrieving Tool:' + err)
        );
      })
    );
  }
}
