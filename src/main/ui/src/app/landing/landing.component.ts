import { Component, OnInit } from '@angular/core';
import { NavItem } from '../shared/models/nav-item';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent {
  navShowBrand: boolean = true;
  leftNavItems: NavItem[] = [];

  rightNavItems: NavItem[] = [
    {
      name: 'Sign Up',
      routerLink: '/register',
      href: '',
      ariaLabel: 'Sign Up'
    },
    {
      name: 'Sign In',
      routerLink: '/login',
      href: '',
      ariaLabel: 'Sign In'
    },
  ];
  constructor() { }

}
