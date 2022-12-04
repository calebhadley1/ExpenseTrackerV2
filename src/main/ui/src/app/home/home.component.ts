import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppState } from '../state/app.state';
import { selectAuthIsAuthenticated } from '../state/selectors/auth.selector';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  // isAuthenticated$: Observable<boolean> = this.store.select(selectAuthIsAuthenticated);

  constructor(private store: Store<AppState>) { }

  ngOnInit(): void {}

}
