import { Component, OnInit } from '@angular/core';
import { NavItem } from '../core/navbar/nav-item';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
  navShowBrand: boolean = true;
  navItems: NavItem[] = [
    {
      name: '1',
      routerLink: '',
      href: '',
      ariaLabel: ''
    },
    {
      name: '2',
      routerLink: '',
      href: '',
      ariaLabel: ''
    },
    {
      name: '3',
      routerLink: '',
      href: '',
      ariaLabel: ''
    }
  ];
  constructor() { }

  ngOnInit(): void {
  }

}
