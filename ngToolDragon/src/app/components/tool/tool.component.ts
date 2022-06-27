import { User } from 'src/app/models/user';
import { ToolService } from './../../services/tool.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Tool } from 'src/app/models/tool';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-tool',
  templateUrl: './tool.component.html',
  styleUrls: ['./tool.component.css']
})
export class ToolComponent implements OnInit {
  private url = environment.baseUrl + 'api/tool';
  toolList: Tool[] = [];
  toolListFull: Tool[] = [];
  tool: Tool = new Tool;
  newTool: Tool = new Tool;
  user: User = new User;

  constructor(
    private http: HttpClient,
    private toolService: ToolService,
    private router: Router,
    private authService: AuthService,
    private userService: UserService
    ) { }

  ngOnInit(): void {
    this.index();
    this.authenticateUser();
  }

  reload() {


  }


  authenticateUser(){
    this.authService.authenticateUser().subscribe({
      next: (result) =>{
        this.user = result;
        if(result.tools) {
        this.toolList = result.tools;
        }
      },
      error: (problem) => {
        console.log(problem);
      }
    })
  }

  index() {
    this.toolService.index().subscribe({
      next: (result) => {
        this.toolListFull = result;
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

  createTool(tool: Tool): void {
    tool.active = true;
    tool.owner = this.user;
    console.log(this.user);
    this.toolService.create(tool).subscribe({
      next: (result) => {
        this.tool = result;
        window.alert('A tool was created!');
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
