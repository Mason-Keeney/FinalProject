import { faDragon, faListCheck, faToolbox, faUser } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { faGear } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  public isCollapsed = false;
  public faDragon = faDragon;
  public faGear = faGear;
  public faToolbox = faToolbox;
  public faListCheck = faListCheck;
  public faUser = faUser;

  constructor(
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
  }

  checkLogin(): boolean{
    return this.authService.checkLogin();
  }

}
