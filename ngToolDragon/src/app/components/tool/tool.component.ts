import { ActivePipe } from './../../pipes/active.pipe';
import { faToolbox } from '@fortawesome/free-solid-svg-icons';
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
  faToolbox = faToolbox;
  search: string = "";
  updateChecker: Tool = new Tool;

  constructor(
    private http: HttpClient,
    private toolService: ToolService,
    private router: Router,
    private authService: AuthService,
    private userService: UserService,
    private activePipe: ActivePipe
    ) { }

  ngOnInit(): void {
    this.indexAll();
    this.authenticateUser();
  }

  reload() {


  }

  checkLogin(): boolean{
    return this.authService.checkLogin();
  }
  setUpdate(tool: Tool): void {

    if(this.updateChecker != tool) {
      this.updateChecker = tool;
    } else {
      this.updateChecker = new Tool;
    }

  }

  authenticateUser(){
    this.authService.authenticateUser().subscribe({
      next: (result) =>{
        this.user = result;
        if(result.tools) {
        this.toolList = this.activePipe.transform(result.tools);
        }
      },
      error: (problem) => {
        console.log(problem);
      }
    })
  }

  indexAll() {
    this.toolService.indexAll().subscribe({
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

  updateTool(id: number | null, tool: Tool): void {
    this.toolService.update(id, tool).subscribe({
      next: (result) => {
      },
      error: (nojoy) => {
        console.log('Tool.update(): error updating Tool:');
        console.log(nojoy);
       },
    })
  }

  destroy(tool: Tool): void {
      this.toolService.destroy(tool.id).subscribe({
        next: (result) => {
          window.alert(this.user.username + "'s tool was deleted");
        },
        error: (nojoy) => {
          console.error('Tool.delete(): error deleting tool:');
          console.error(nojoy);
          this.reload();
        },
      });
  }

}
