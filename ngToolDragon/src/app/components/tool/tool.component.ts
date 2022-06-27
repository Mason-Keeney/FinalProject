import { ToolService } from './../../services/tool.service';
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
  private url = environment.baseUrl + 'api/tool';
  toolList: Tool[] = [];
  tool: Tool = new Tool;

  constructor(
    private http: HttpClient,
    private toolService: ToolService
    ) { }

  ngOnInit(): void {
    this.index();
  }


  index() {
    this.toolService.index().subscribe({
      next: (result) => {
        this.toolList = result;
      },
      error: (nojoy) => {
       console.log('Tool.index(): error retrieving Tools:');
       console.log(nojoy);
      },
    });
  }

  show(tool: Tool): void {
    this.toolService.show(tool.id).subscribe({
      next: (result) => {
        this.tool = result;
      },
      error: (nojoy) => {
       console.log('Tool.show(): error retrieving Tool:');
       console.log(nojoy);
      },
    });
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
