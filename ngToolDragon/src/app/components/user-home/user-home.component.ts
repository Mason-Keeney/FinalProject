import { DatePipe } from '@angular/common';
import { UserService } from './../../services/user.service';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { ViewChild, AfterViewInit } from '@angular/core';
import { EdituserComponent } from '../edituser/edituser.component';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})


export class UserHomeComponent implements OnInit {

  user: User | null = null;
  editingUser: Boolean = false;
  faUser = faUser;
  today = this.datePipe.transform(new Date());

  @ViewChild(EdituserComponent, { static: false })
  editUserComponent!: EdituserComponent;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private datePipe: DatePipe
  ) {}

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

  toggleEdituser(){
    this.editingUser = !this.editingUser;
    if(this.editingUser){

    } else {
      this.editUserComponent.editUser = new User();
    }
  }

  updateLastLogin(){
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

  // ngDoCheck(){
  //   if(this.user?.lastLogin != this.today){
  //     this.updateLastLogin();
  //   }
  // }


  ngOnInit(): void {
    this.authenticateUser();
  }

  ngAfterContentInit(): void {

  }

  ngViewAfterInit(): void{
    this.editUserComponent.editUser = this.user;
  }

}
