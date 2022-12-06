import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { NavItem } from '../core/navbar/nav-item';
import { Expense } from '../shared/models/expense';
import { ExpensesApiActions } from '../state/actions/expense.actions';
import { AppState } from '../state/app.state';
import { selectExpenses } from '../state/selectors/expense.selectors';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  expenses$: Observable<Expense[]> = this.store.select(selectExpenses);

  navShowBrand: boolean = true;
  leftNavItems: NavItem[] = [];

  rightNavItems: NavItem[] = [
    {
      name: 'Some item',
      href: '',
      ariaLabel: 'Some item'
    }
  ];

  constructor(private store: Store<AppState>) { }

  ngOnInit(): void {
    this.store.dispatch(ExpensesApiActions.get_all_expenses());
  }

}
