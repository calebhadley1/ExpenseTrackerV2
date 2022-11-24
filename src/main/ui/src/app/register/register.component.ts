import { Component, OnInit } from '@angular/core';
import { NavItem } from '../core/navbar/nav-item';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
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

  constructor() { }

  ngOnInit(): void {
  }

}
