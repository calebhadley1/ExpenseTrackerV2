import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Expense } from 'src/app/shared/models/expense';
import { ExpensesApiActions } from 'src/app/state/actions/expense.actions';
import { AppState } from 'src/app/state/app.state';
import { selectExpenses } from 'src/app/state/selectors/expense.selectors';

@Component({
  selector: 'app-home-transaction-table',
  templateUrl: './home-transaction-table.component.html',
  styleUrls: ['./home-transaction-table.component.scss']
})
export class HomeTransactionTableComponent implements OnInit {
  expenses$: Observable<readonly Expense[]> = this.store.select(selectExpenses);

  displayedColumns: string[] = ['name', 'description', 'amount'];
  dataSource = this.expenses$;

  constructor(private store: Store<AppState>) { }

  ngOnInit(): void {
    this.store.dispatch(ExpensesApiActions.get_all_expenses());
  }

}
