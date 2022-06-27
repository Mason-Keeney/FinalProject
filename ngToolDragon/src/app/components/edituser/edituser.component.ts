import { UserService } from './../../services/user.service';
import { AddressService } from './../../services/address.service';
import { AuthService } from './../../services/auth.service';
import { User } from 'src/app/models/user';
import { UserHomeComponent } from './../user-home/user-home.component';
import { HomeComponent } from './../home/home.component';
import { Component, Input, OnInit } from '@angular/core';
import { applyStyles } from '@popperjs/core';
import { faAddressCard, faLock, faLockOpen, faUserPen, faShieldAlt} from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent implements OnInit {

  @Input() editUser: any;

  constructor(
    private authService: AuthService,
    private addressService: AddressService,
    private userService: UserService
  ) { }

  faUserPen = faUserPen;
  faAddressCard = faAddressCard;
  editPassword: boolean = false;
  faLock = faLock;
  faLockOpen = faLockOpen;
  faShieldAlt = faShieldAlt;
  editAddress: boolean = false;

  saveEdit(user: User) {
    if(user){
      this.userService.update(user.id, user).subscribe({
        next: (result) => {
          user = result;
        },
        error: (problem) => {
          console.log("EditUserHttpComponent Error: unable to update lastLogin: ");
          console.log(problem);
        }
      })
    }
  }

  checkEditPassword(): string {
    let btnclass: string = "btn btn-success";
    if(this.editPassword){
      btnclass = "btn btn-warning"
    }
    return btnclass;
  }

  editSecurityDetails(user: User){
    if(user){
      this.authService.register(user).subscribe({
        next: (result) => {
          this.editUser = result;
        },
        error: (problem) => {
          console.log("EditUserHttpComponent Error: unable to save security detail changes");
          console.log(problem);


        }
      })
    }
  }

  setEditAddress(){
    this.editAddress = this.editUser.address ? true : false;
  }

  ngOnInit(): void {
    this.setEditAddress();
  }

}
