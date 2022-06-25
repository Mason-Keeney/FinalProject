import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { throwError } from 'rxjs/internal/observable/throwError';
import { catchError } from 'rxjs/internal/operators/catchError';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private url = environment.baseUrl + 'api/users';

  constructor(
    private http: HttpClient,
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

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('User.index(): error retrieving Users:' + err)
        );
      })
    );
  }

  show(id: number | null): Observable<User> {
    return this.http.get<User>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('User.show(): error retrieving User:' + err)
        );
      })
    );
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(this.url, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('User.create(): error retrieving User:' + err)
        );
      })
    );
  }

  update(id: number | null, user: User): Observable<User> {
    return this.http.put<User>(this.url + '/' + id, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('User.update(): error retrieving User:' + err)
        );
      })
    );
  }
  deactivate(id: number | null, user: User): Observable<User> {
    user.enabled = false;
    return this.http.put<User>(this.url + '/' + id, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('User.update(): error retrieving User:' + err)
        );
      })
    );
  }

  destroy(id: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'User.destroy(): error retrieving User:' + err
            )
        );
      })
    );
  }
}
