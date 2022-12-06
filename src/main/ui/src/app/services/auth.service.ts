import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../shared/models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Buffer } from "buffer";
import { Token } from '../shared/models/token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { 

  }

  register(user: User): Observable<Response> {
    const url = `${environment.BASE_URL}/register`;
    return this.http.post<Response>(url, user);
  }

  token(username: string, password: string): Observable<Token> {
    const url = `${environment.BASE_URL}/token`;
    const basicAuth = Buffer.from(`${username}:${password}`).toString('base64')
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: `Basic ${basicAuth}`
      })
    };
    return this.http.post<Token>(url, {}, httpOptions);
  }
}
