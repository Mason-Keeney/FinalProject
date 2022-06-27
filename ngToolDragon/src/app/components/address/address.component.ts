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


  constructor(
    private router: Router,
    private addressSvc: AddressService

  ) { }

  ngOnInit(): void {
  }

  hardReload() {
  }

  showAddress(address: Address): void {
    this.addressSvc.show(address.id).subscribe({
      next: (result) => {
        this.address = new Address();
        // this.address.push(address);
      },
      error: (nojoy) => {
        console.error(
          'HomeListHttpComponent.addAddress(): error creating address:'
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
          this.hardReload();
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
        this.hardReload;
        window.alert(' had a address created!');
        // this.address.push(address);
      },
      error: (nojoy) => {
        console.error(
          'HomeListHttpComponent.addAddress(): error creating address:'
        );
        console.error(nojoy);
      },
    });
}}
