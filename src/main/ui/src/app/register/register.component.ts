import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { NavItem } from '../shared/models/nav-item';
import { User } from '../shared/models/user';
import { register } from '../state/actions/auth.actions';
import { AppState } from '../state/app.state';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  navShowBrand: boolean = true;
  leftNavItems: NavItem[] = [];
  
  rightNavItems: NavItem[] = [
    {
      name: 'Sign In',
      routerLink: '/login',
      href: '',
      ariaLabel: 'Sign In'
    }
  ];

  constructor(private store: Store<AppState>) { }

  register(user: User) {
    console.log('emitted' + user)
    this.store.dispatch(register({user}));
  }

}
