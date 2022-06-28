import { Component, Input, OnInit } from '@angular/core';
import { Tool } from 'src/app/models/tool';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ProjectService } from 'src/app/services/project.service';
import { ToolService } from 'src/app/services/tool.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-inspect-project',
  templateUrl: './inspect-project.component.html',
  styleUrls: ['./inspect-project.component.css']
})
export class InspectProjectComponent implements OnInit {
  user: User = new User;
  toolListFull: Tool[] = [];
  toolList: Tool[] = [];

  @Input() project: any;

  constructor(
    private projectServ: ProjectService,
    private userServ: UserService,
    private authService: AuthService,
    private toolService: ToolService,
  ) { }

  ngOnInit(): void {
    this.toolIndex();
    this.authenticateUser();
  }

  authenticateUser(){
    this.authService.authenticateUser().subscribe({
      next: (result) =>{
        this.user = result;
      },
      error: (problem) => {
        console.log(problem);
      }
    })
  }

  toolIndex() {
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


}
