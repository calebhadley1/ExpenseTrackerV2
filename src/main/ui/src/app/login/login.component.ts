import { Component, OnInit } from '@angular/core';
import { NavItem } from '../core/navbar/nav-item';

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
  
  constructor() { }

}
