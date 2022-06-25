import { RegisterComponent } from './../register/register.component';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser: User = new User();

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login(user: User){
    if(user.username != null && user.password != null){
      this.authService.login(user.username, user.password).subscribe({

        next: (user) => {
          this.router.navigateByUrl("/userhome")
        },
        error: (problem) => {
          console.log("LoginHttpComponent Error: unable to log in");
          console.log(problem);

        }
      })

    }
  }

  showRegisterForm(){
    this.router.navigateByUrl("/register");
  }

  ngOnInit(): void {
  }

}
