import { Injectable } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../shared/models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Buffer } from "buffer";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // user$: Observable<User>;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      // Authorization: 'Basic ' + btoa(`${username}:${password}`)
    })
  };

  constructor(private http: HttpClient) { 

  }

  register(user: User): Observable<Response> {
    const url = `${environment.BASE_URL}/register`;
    return this.http.post<Response>(url, user);
  }

  token(username: string, password: string): Observable<string> {
    console.log(username, password)
    const url = `${environment.BASE_URL}/token`;

    // const httpOptions = {
    //   headers: headers
    // };
    // this.httpOptions = this.httpOptions.headers.set('Authorization', 'Basic ' + btoa(`${username}:${password}`));
    // this.httpOptions.headers.set('something', username);
    const basicAuth = Buffer.from(`${username}:${password}`).toString('base64')
    console.log(basicAuth)
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: `Basic ${basicAuth}`,
        'test': 'test',
        test2: 'test'
      })
    };

    // console.log(this.httpOptions)
    return this.http.post<string>(url, {}, httpOptions);
  }
}
