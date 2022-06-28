import { environment } from './../../../environments/environment';
import { Router } from '@angular/router';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import {
  faAddressCard,
  faLock,
  faLockOpen,
  faShieldAlt,
  faUserPen,
  faUserSlash,
  faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import { User } from 'src/app/models/user';
import { AddressComponent } from '../address/address.component';
import { AddressService } from './../../services/address.service';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';

@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css'],
})
export class EdituserComponent implements OnInit {
  @Input() editUser: any;

  @ViewChild(AddressComponent, { static: false })
  addressComponent!: AddressComponent;

  constructor(
    private authService: AuthService,
    private addressService: AddressService,
    private userService: UserService,
    private router: Router
  ) {}

  faUserPen = faUserPen;
  faAddressCard = faAddressCard;
  editPassword: boolean = false;
  faLock = faLock;
  faLockOpen = faLockOpen;
  faShieldAlt = faShieldAlt;
  editAddress: boolean = false;
  faUserSlash = faUserSlash;
  faCircleArrowLeft = faCircleArrowLeft;

  saveEdit(user: User) {
    if (user) {
      this.userService.update(user.id, user).subscribe({
        next: (result) => {
          user = result;
        },
        error: (problem) => {
          console.log(
            'EditUserHttpComponent Error: unable to update lastLogin: '
          );
          console.log(problem);
        },
      });
    }
  }

  checkEditPassword(): string {
    let btnclass: string = 'btn btn-success';
    if (this.editPassword) {
      btnclass = 'btn btn-warning';
    }
    return btnclass;
  }

  editSecurityDetails(user: User) {
    if (user) {
      this.authService.register(user).subscribe({
        next: (result) => {
          this.editUser = result;
        },
        error: (problem) => {
          console.log(
            'EditUserHttpComponent Error: unable to save security detail changes'
          );
          console.log(problem);
        },
      });
    }
  }

  deleteUser(id: number){
    this.userService.destroy(id).subscribe({
      next: () => {
        this.authService.logout;
        this.router.navigateByUrl("/home");
      },
      error: (problem) =>{
        console.log("EditUserHttpComponent Error: unable to delete user");
        console.log(problem);

      }
    })
  }


  setEditAddress() {
    this.editAddress = this.editUser.address ? true : false;
  }

  startAddressEdit() {
    this.router.navigateByUrl('/address');
  }

  ngOnInit(): void {
    this.setEditAddress();
  }


}
