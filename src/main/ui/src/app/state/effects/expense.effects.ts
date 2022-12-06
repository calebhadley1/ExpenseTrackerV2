import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { Store } from "@ngrx/store";
import { exhaustMap, map, catchError, of } from "rxjs";
import { ExpenseService } from "src/app/services/expense.service";
import { Expense } from "src/app/shared/models/expense";
import { getToken, getTokenSuccess, getTokenFail } from "../actions/auth.actions";
import { ExpensesApiActions } from "../actions/expense.actions";
import { AppState } from "../app.state";

@Injectable()
export class ExpenseEffects {

  constructor(
    private actions$: Actions,
    private expenseService: ExpenseService,
    private store: Store<AppState>
  ) {}

  getAllExpenses$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(ExpensesApiActions.get_all_expenses),
      exhaustMap((action) => {
        console.log('get_all_expenses')
        return this.expenseService.getAllExpenses()
        .pipe(
          map((expenses: Expense[]) => {
            console.log('success')
            return ExpensesApiActions.get_all_expenses_success({ expenses })
          }),
          catchError((error) => {
            console.log(error);
            return of( ExpensesApiActions.get_all_expenses_fail({ error }))
          })
        )}
      )
    )
  })

}