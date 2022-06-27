import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginUser: User = new User();
  today = new Date();

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) {}

  login(user: User) {
    if (user.username != null && user.password != null) {
      this.authService.login(user.username, user.password).subscribe({
        next: (user) => {
          this.router.navigateByUrl('/userhome');
        },
        error: (problem) => {
          console.log('LoginHttpComponent Error: unable to log in');
          console.log(problem);
          window.alert(
            'The username or password you entered is not correct, please try again'
          );
        },
      });
    }
  }

  showRegisterForm() {
    this.router.navigateByUrl('/register');
  }

  ngOnInit(): void {}
}
