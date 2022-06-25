import { faDragon } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  public isCollapsed = false;
  public faDragon = faDragon;
  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  checkLogin(): boolean{
    return this.authService.checkLogin();
  }

}
