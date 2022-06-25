import { AuthService } from './../../services/auth.service';
import { User } from 'src/app/models/user';
import { UserHomeComponent } from './../user-home/user-home.component';
import { HomeComponent } from './../home/home.component';
import { Component, Input, OnInit } from '@angular/core';
import { applyStyles } from '@popperjs/core';


@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent implements OnInit {

  @Input() editUser: any;

  constructor(
    private authService: AuthService
  ) { }


  ngOnInit(): void {
  }

}
