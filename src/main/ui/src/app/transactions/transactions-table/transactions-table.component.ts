import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Expense } from 'src/app/shared/models/expense';
import { ExpensesApiActions } from 'src/app/state/actions/expense.actions';
import { AppState } from 'src/app/state/app.state';
import { selectExpenses } from 'src/app/state/selectors/expense.selectors';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-transactions-table',
  templateUrl: './transactions-table.component.html',
  styleUrls: ['./transactions-table.component.scss']
})
export class TransactionsTableComponent implements OnInit {
  expenses$: Observable<Expense[]> = this.store.select(selectExpenses);
  
  displayedColumns: string[] = ['name', 'description', 'amount'];
  dataSource = new MatTableDataSource<Expense>();
  selection = new SelectionModel<Expense>(true, []);

  constructor(private store: Store<AppState>) { }

  ngOnInit(): void {
    this.store.dispatch(ExpensesApiActions.get_all_expenses());
    this.expenses$.subscribe(res => this.dataSource.data = res);
  }

  /*
    Table CRUD Methods
  */

  addNewRow(): void {
    
  }

  addExpense(): void {

  }

  removeExpense(): void {
  }


  /*
    Table Selection Methods
  */

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  toggleAllRows() {
    if (this.isAllSelected()) {
      this.selection.clear();
      return;
    }

    this.selection.select(...this.dataSource.data);
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: Expense): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.id + 1}`;
  }

  /*
    Table filtering
  */
  applyFilter(event: Event) {
      const filterValue = (event.target as HTMLInputElement).value;
      this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
