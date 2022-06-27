import { UserService } from './../../services/user.service';
import { faAddressCard } from '@fortawesome/free-solid-svg-icons';
import { Address } from './../../models/address';

import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { AddressService } from 'src/app/services/address.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {
address: Address = new Address;
addresses: Address[] = [];
user: User = new User;
faAddressCard = faAddressCard;
newAddress = new Address();





  constructor(
    private router: Router,
    private authService: AuthService,
    private addressSvc: AddressService,
    private userService: UserService

  ) { }

  ngOnInit(): void {
    this.authenticateUser();
  }

  reload() {
    this.showAddress(this.address);
  }

  authenticateUser(){
    this.authService.authenticateUser().subscribe({
      next: (result) =>{
        this.user = result;
        if(result.address) {
        this.address = result.address;
        }
      },
      error: (problem) => {
        console.log(problem);
      }
    })
  }


  searchAddress(search: String): void {
    this.addressSvc.index().subscribe({
      next: (result) => {
        // this.addresses.push(result);
      },
      error: (nojoy) => {
        console.error(
          'AddressListHttpComponent.searchAddress(): error getting addresses'
        );
        console.error(nojoy);
      },
    })

  }

  showAddress(address: Address): void {
    this.addressSvc.show(address.id).subscribe({
      next: (result) => {
        this.address = result;
      },
      error: (nojoy) => {
        console.error(
          'AddressListHttpComponent.addAddress(): error creating address:'
        );
        console.error(nojoy);
      },
    });
  }

  deleteAddress(address: Address): void {
    if (address.id) {
      this.addressSvc.destroy(address.id).subscribe({
        next: (result) => {
          window.alert(name + "'s address was deleted");
        },
        error: (nojoy) => {
          console.error(
            'HomeListHttpComponent.deleteAddress(): error deleteing address:'
          );
          console.error(nojoy);
          this.reload();
        },
      });
    }
  }

  updateAddress(id: number | null, address: Address): void {
      this.addressSvc.update(id, address).subscribe({
      next: (result) => {
        address = result;
        if(address){
          window.alert("Address has been updated")
        }
      },
      error: (nojoy) => {
        console.error(
          'HomeListHttpComponent.updateAddress(): error updating address:'
        );
        console.error(nojoy);
      },
    });
  }

  addAddress(address: Address): void {
    this.addressSvc.create(address).subscribe({
      next: (result) => {
        this.address = result;
        window.alert('An address was created!');
        console.log(this.address);

        this.user.address = this.address;
        console.log(this.user.address);

        this.updateUser(this.user);
        console.log(this.user.address);

      },
      error: (nojoy) => {
        console.error(
          'HomeListHttpComponent.addAddress(): error creating address:'
        );
        console.error(nojoy);
      },
    });
}

updateUser(user: User){
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



}


