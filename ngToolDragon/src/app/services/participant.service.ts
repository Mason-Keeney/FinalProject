import { Participant } from './../models/participant';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, partition } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {
  private url = environment.baseUrl + 'api/participants';

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

  index(): Observable<Participant[]> {
    return this.http.get<Participant[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('Participant.index(): error retrieving Participants:' + err)
        );
      })
    );
  }

  show(pid: number | null, uid: number | null): Observable<Participant> {
    return this.http.get<Participant>(this.url + '/' + pid + '/' + uid, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('Participant.show(): error retrieving Participant:' + err)
        );
      })
    );
  }

  create(participant: Participant): Observable<Participant> {
    return this.http.post<Participant>(this.url, participant, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('Particpant.create(): error retrieving Participant:' + err)
        );
      })
    );
  }

  update(pid: number | null, uid: number | null, participant: Participant): Observable<Participant> {
    return this.http.put<Participant>(this.url + '/' + pid + '/' + uid, participant, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('Participant.update(): error retrieving Participant:' + err)
        );
      })
    );
  }

  destroy(pid: number | null, uid: number | null): Observable<boolean> {
    return this.http.delete<boolean>(this.url + '/' + pid + '/' + uid, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'Participant.destroy(): error destroying Participant:' + err
            )
        );
      })
    );
  }
}
