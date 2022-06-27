import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Tool } from 'src/app/models/tool';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-tool',
  templateUrl: './tool.component.html',
  styleUrls: ['./tool.component.css']
})
export class ToolComponent implements OnInit {
  private url = environment.baseUrl + 'api/adresses';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  index(): Observable<Tool[]> {
    return this.http.get<Tool[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.index(): error retrieving Tools:' + err)
        );
      })
    );
  }

  show(id: number | null): Observable<Tool> {
    return this.http.get<Tool>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.show(): error retrieving Tool:' + err)
        );
      })
    );
  }

  create(tool: Tool): Observable<Tool> {
    return this.http.post<Tool>(this.url, tool).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.create(): error retrieving Tool:' + err)
        );
      })
    );
  }

  update(id: number | null, tool: Tool): Observable<Tool> {
    return this.http.put<Tool>(this.url + '/' + id, tool).pipe(
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
    return this.http.put<Tool>(this.url + '/' + id, tool).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.update(): error retrieving Tool:' + err)
        );
      })
    );
  }

  destroy(id: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Tool.destroy(): error retrieving Tool:' + err)
        );
      })
    );
  }


}
