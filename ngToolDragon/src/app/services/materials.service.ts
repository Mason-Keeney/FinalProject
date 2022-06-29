import { Material } from './../models/material';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MaterialsService {
  private url = environment.baseUrl + 'api/materials';

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

  index(): Observable<Material[]> {
    return this.http.get<Material[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Material.index(): error retrieving Materials:' + err)
        );
      })
    );
  }

  show(id: number | null): Observable<Material> {
    return this.http.get<Material>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Material.show(): error retrieving Material:' + err)
        );
      })
    );
  }

  create(material: Material): Observable<Material> {
    return this.http.post<Material>(this.url, material, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Material.create(): error creating Material:' + err)
        );
      })
    );
  }

  update(id: number | null, material: Material): Observable<Material> {
    return this.http.put<Material>(this.url + '/' + id, material, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Material.update(): error updating Material:' + err)
        );
      })
    );
  }

  destroy(id: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Material.destroy(): error destroying Material:' + err)
        );
      })
    );
  }
}
