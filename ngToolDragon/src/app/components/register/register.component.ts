import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User = new User();

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  registerUser(newUser: User){
    this.authService.register(newUser).subscribe({
      next:(user) =>{
        console.log(user);
        if(user.username && newUser.password){
          this.authService.login(user.username, newUser.password).subscribe({
            next: (user) =>{
              this.router.navigateByUrl("/home");
            },
            error: (problem) => {
              console.log("RegisterHtmlComponent error: unable to complete login.");
              console.log(problem);

            }
          })
        }
      },
      error: (problem) => {
        console.log("RegisterHtmlComponent error: unable to register user.");
        console.log(problem);
        }
      })

    }

  ngOnInit(): void {
  }

}
