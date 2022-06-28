import { ActivePipe } from './../../pipes/active.pipe';
import { Project } from './../../models/project';
import { DatePipe } from '@angular/common';
import { UserService } from './../../services/user.service';
import { faUser, faUserSlash } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { ViewChild, AfterViewInit } from '@angular/core';
import { EdituserComponent } from '../edituser/edituser.component';
import { Tool } from 'src/app/models/tool';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})


export class UserHomeComponent implements OnInit{

  user: User | null = null;
  editingUser: Boolean = false;
  faUser = faUser;
  faUserSlash = faUserSlash;
  today = new Date();
  todayString = this.datePipe.transform(this.today);
  userList: User[] = [];
  projectList: Project[] = [];
  isAdmin = false;


  @ViewChild(EdituserComponent, { static: false })
  editUserComponent!: EdituserComponent;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private datePipe: DatePipe,
    private activePipe: ActivePipe,
    private projectService: ProjectService
  ) {}


  authenticateUser(){
    this.authService.authenticateUser().subscribe({
      next: (result) =>{
        console.log(this.todayString)
        this.user = result;
        if(this.user.role === "role_admin"){
           this.indexUsers();
           this.indexProjects();
           this.isAdmin = true;
        }
      },
      error: (problem) => {
        console.log(problem);
      }
    })
  }

  toggleEdituser(){
    this.editingUser = !this.editingUser;
    if(this.editingUser){

    } else {
      this.editUserComponent.editUser = new User();
    }
  }

  updateLastLogin(){
    console.log(this.user)
    if(this.user){
      // this.user.lastLogin = this.today;
      this.userService.update(this.user.id, this.user).subscribe({
        next: (result) => {
          this.user = result;

        },
        error: (problem) => {
          console.log("UserHomeHttpComponent Error: unable to update lastLogin: ");
          console.log(problem);
        }
      })
    }
  }

  indexUsers(){
      this.userService.index().subscribe({
        next: (result) => {
          this.userList = result;
        },
        error: (problem) => {
          console.log("UserHomeHttpComponent Error: unable to populate UserList");
          console.log(problem);
        }
      })
  }
  reload(){
    this.indexUsers()
  }

  deleteUser(deleteUser: User){
    this.userService.destroy(deleteUser.id).subscribe({
      next: () => {
        this.reload();
      },
      error: (problem) => {
        console.log("UserHomeHttpComponent Error: unable to delete user");
        console.log(problem);
      }
    })
  }

  deleteProject(deleteProject: Project){
    console.log(deleteProject);
  }

  indexProjects(){
    this.projectService.index().subscribe({
      next: (result) => {
        this.projectList = result;
      },
      error: (problem) => {
        console.log("UserHomeHttpComponent Error: unable to populate UserList")
        console.log(problem);
      }
    })
  }

  startProjectView(project: Project){
    console.log(project);

  }

  startToolView(tool: Tool){
    console.log(tool);

  }

  ngOnInit(): void {
    this.authenticateUser();


  }

  ngAfterContentInit(): void {

  }

}
