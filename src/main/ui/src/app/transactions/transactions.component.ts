import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { NavItem } from '../shared/models/nav-item';
import { ExpensesApiActions } from '../state/actions/expense.actions';
import { AppState } from '../state/app.state';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit {
  navShowBrand: boolean = false;
  leftNavItems: NavItem[] = [];
  rightNavItems: NavItem[] = [
    {
      name: 'Sign out',
      routerLink:'/login',
      href: '',
      ariaLabel: 'Sign out'
    }
  ];
  sideNavItems: NavItem[] = [
    {
      name: 'Home',
      routerLink:'/home',
      href: '',
      activated: false,
      ariaLabel: 'Home'
    },
    {
      name: 'Transactions',
      routerLink:'/transactions',
      href: '',
      activated: true,
      ariaLabel: 'Transactions'
    },
    {
      name: 'Trends',
      routerLink:'/trends',
      href: '',
      activated: false,
      ariaLabel: 'Trends'
    },
  ]

  constructor(private store: Store<AppState>) { }

  ngOnInit(): void {
    this.store.dispatch(ExpensesApiActions.get_all_expenses());
  }

}
