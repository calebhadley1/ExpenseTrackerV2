import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { select, Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Expense } from '../shared/models/expense';
import { Token } from '../shared/models/token';
import { AppState } from '../state/app.state';
import { selectAuthToken } from '../state/selectors/auth.selector';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {
  token$: Observable<Token> = this.store.select(selectAuthToken);
  token: string = '';

  constructor(private http: HttpClient, private store: Store<AppState>) {
    this.token$.subscribe((res) => this.token = res.token);
  }

  getAllExpenses(): Observable<Expense[]> {
    const url = `${environment.BASE_URL}/expense`;
    const bearer = `Bearer ${this.token}`
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: bearer,
      })
    };
    return this.http.get<Expense[]>(url, httpOptions);
  }
}
