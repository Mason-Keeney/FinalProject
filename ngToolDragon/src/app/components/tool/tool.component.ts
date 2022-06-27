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

  reload() {
    this.tool.id = 1;
    this.show(this.tool);
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

  create(tool: Tool): void {
    this.toolService.create(tool).subscribe({
      next: (result) => {
        this.tool = result;
        window.alert('An address was created!');
      },
      error: (nojoy) => {
        console.error(
          'Tool.create(): error creating tool:'
        );
        console.error(nojoy);
      },
    })
  }

  update(id: number | null, tool: Tool): void {
    this.toolService.update(id, tool).subscribe({
      next: (result) => {
      },
      error: (nojoy) => {
        console.log('Tool.update(): error updating Tool:');
        console.log(nojoy);
       },
    })
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

  destroy(tool: Tool): void {
      this.toolService.destroy(tool.id).subscribe({
        next: (result) => {
          window.alert(name + "'s tool was deleted");
        },
        error: (nojoy) => {
          console.error('Tool.delete(): error deleting tool:');
          console.error(nojoy);
          this.reload();
        },
      });
  }

}
