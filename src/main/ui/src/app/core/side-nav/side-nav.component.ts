import { Component, Input, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { NavItem } from '../../shared/models/nav-item';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.scss']
})
export class SideNavComponent implements OnInit {
  @Input() sideNavItems: NavItem[] = [];
  activatedItem: string = '';
  constructor(
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
    ) { 
    this.matIconRegistry.addSvgIcon(
      "piggybank",
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/undraw_credit_card_re_blml.svg")
    );
  }

  getActiveItem(): void {
    for(const item of this.sideNavItems) {
      if(item.activated){
        this.activatedItem = item.name;
        return;
      }
    }
    this.activatedItem = '';
  }

  ngOnInit(): void {
    this.getActiveItem();
  }

}
