import { Address } from './../../models/address';

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { AddressService } from 'src/app/services/address.service';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {
address: Address = new Address;
addresses: Address[] = [];


  constructor(
    private router: Router,
    private addressSvc: AddressService

  ) { }

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.address.id = 1;
    this.showAddress(this.address);
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
      },
      error: (nojoy) => {
        console.error(
          'HomeListHttpComponent.addAddress(): error creating address:'
        );
        console.error(nojoy);
      },
    });
}}
