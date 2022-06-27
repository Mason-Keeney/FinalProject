import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from '../models/address';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AddressService {
  private url = environment.baseUrl + 'api/addresses';

  constructor(private http: HttpClient,
    private authService: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return options;
  }

  index(): Observable<Address[]> {
    return this.http.get<Address[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Address.index(): error retrieving Addresses:' + err)
        );
      })
    );
  }

  show(id: number | null): Observable<Address> {
    return this.http.get<Address>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Address.show(): error retrieving Address:' + err)
        );
      })
    );
  }

  create(address: Address): Observable<Address> {
    return this.http.post<Address>(this.url, address, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Address.create(): error retrieving Address:' + err)
        );
      })
    );
  }

  update(id: number | null, address: Address): Observable<Address> {
    return this.http.put<Address>(this.url + '/' + id, address, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Address.update(): error retrieving Address:' + err)
        );
      })
    );
  }
  deactivate(id: number | null, address: Address): Observable<Address> {
    address.active = false;
    return this.http.put<Address>(this.url + '/' + id, address, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Address.update(): error retrieving Address:' + err)
        );
      })
    );
  }

  destroy(id: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Address.destroy(): error retrieving Address:' + err)
        );
      })
    );
  }
}
