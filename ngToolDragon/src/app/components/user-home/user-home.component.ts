import { ToolService } from './../../services/tool.service';
import { ActivePipe } from './../../pipes/active.pipe';
import { Project } from './../../models/project';
import { DatePipe } from '@angular/common';
import { UserService } from './../../services/user.service';
import { faUser, faUserSlash, faUserPen, faTrashCan, faPenToSquare, faFloppyDisk, faCircleArrowLeft } from '@fortawesome/free-solid-svg-icons';
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
  faUserPen = faUserPen;
  faTrashCan = faTrashCan;
  faPenToSquare = faPenToSquare;
  faFloppyDisk = faFloppyDisk;
  faCircleArrowLeft = faCircleArrowLeft;
  today = new Date();
  todayString = this.datePipe.transform(this.today);
  userList: User[] = [];
  projectList: Project[] = [];
  isAdmin = false;
  toolList: Tool[] = [];
  userSearch = "";
  projectSearch = "";
  toolSearch = "";
  adminEditUser: User | null = null;
  adminEditProject: Project | null = null;
  projectStartDate: string = "";
  projectEndDate: string = "";
  adminEditTool: Tool | null = null;



  @ViewChild(EdituserComponent, { static: false })
  editUserComponent!: EdituserComponent;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private datePipe: DatePipe,
    private activePipe: ActivePipe,
    private projectService: ProjectService,
    private toolService: ToolService
  ) {}


  authenticateUser(){
    this.authService.authenticateUser().subscribe({
      next: (result) =>{
        console.log(this.todayString)
        this.user = result;
        if(this.user.role === "role_admin"){
           this.indexUsers();
           this.indexProjects();
           this.indexTools();
           this.isAdmin = true;
        }
      },
      error: (problem) => {
        console.log(problem);
      }
    })
  }

  startAdminEditUser(user: User){
    if (this.adminEditUser === user){
        this.adminEditUser = null;
    } else {
      this.adminEditUser = user;
    }
  }

  startAdminEditProject(project: Project){
    if(this.adminEditProject != project){
      this.adminEditProject = project;
    } else {
      this.adminEditProject = null;
    }

  }

  startAdminEditTool(tool: Tool){
    if(this.adminEditTool != tool){
      this.adminEditTool = tool
    } else{
      this.adminEditTool = null;
    }
  }

  updateProject(project: Project){
    if(this.projectStartDate != ""){
      project.startDate = new Date(this.projectStartDate);
    }
    if(this.projectEndDate != ""){
      project.estimatedEndDate = new Date(this.projectEndDate);
    }
    this.projectService.update(project.id, project).subscribe({
      next: (result) => {
        this.adminEditProject = null;
      },
      error: (problem) => {
        console.log("UserHomeHttpComponent Error: Unable to update project");
        console.log(problem);
      }
    })
  }

  updateTool(tool: Tool){
    this.toolService.update(tool.id, tool).subscribe({
      next: (result) => {
      this.adminEditTool = null;
      },
      error: (problem) => {
        console.log("UserHomeHttpComponent Error: Unable to update tool");
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
    this.indexUsers();
    this.indexProjects();
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
    this.projectService.destroy(deleteProject.id).subscribe({
      next: () => {
        this.reload();
      },
      error: (problem) => {
        console.log("UserHomeHttpComponent Error: unable to delete project");
        console.log(problem);


      }
    })
  }

  deleteTool(deleteTool: Tool){
    this.toolService.destroy(deleteTool.id).subscribe({
      next:() => {
        this.reload();
      },
      error: (problem) => {
        console.log("UserHomeHttpComponent Error: unable to delete tool");
        console.log(problem);
      }
    })

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

  indexTools(){
    this.toolService.index().subscribe({
      next: (result) => {
        this.toolList = this.activePipe.transform(result);
      },
      error: (problem) => {
        console.log("UserHomeHttpComponent Error: unable to populate ToolList");
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
