import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { NavItem } from '../core/navbar/nav-item';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
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

  ngOnInit(): void {
  }

}
