import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { NavItem } from '../core/navbar/nav-item';
import { Credentials } from '../shared/models/user';
import { getToken } from '../state/actions/auth.actions';
import { AppState } from '../state/app.state';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  navShowBrand: boolean = true;
  leftNavItems: NavItem[] = [];

  rightNavItems: NavItem[] = [
    {
      name: 'Sign Up',
      routerLink: '/register',
      href: '',
      ariaLabel: 'Sign Up'
    }
  ];
  
  constructor(private store: Store<AppState>) { }

  login(credentials: Credentials) {
    console.log('emitted' + credentials)
    this.store.dispatch(getToken(credentials));
  }

}
