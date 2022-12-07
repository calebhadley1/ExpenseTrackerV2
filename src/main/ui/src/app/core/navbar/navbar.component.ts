import { Component, Input, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { NavItem } from '../../shared/models/nav-item';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input() showBrand: boolean = false;
  @Input() leftNavItems: NavItem[] = [];
  @Input() rightNavItems: NavItem[] = [];

  constructor(
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
  ) { 
    this.matIconRegistry.addSvgIcon(
      "piggybank",
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/undraw_credit_card_re_blml.svg")
    );
  }

}
