import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User = new User();

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  checkLogin(): boolean{
    return this.authService.checkLogin();
  }


  ngOnInit(): void {
  }

}
