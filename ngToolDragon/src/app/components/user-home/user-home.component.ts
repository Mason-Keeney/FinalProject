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

  user: User = new User();
  editingUser: Boolean = false;
  faUser = faUser;

  @ViewChild(EdituserComponent, { static: false })
  editUserComponent!: EdituserComponent;

  constructor(
    private authService: AuthService
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



  ngOnInit(): void {
    this.authenticateUser();
  }

  ngViewAfterInit(): void{
    this.editUserComponent.editUser = this.user;
  }

}
