import { createFeatureSelector, createSelector } from "@ngrx/store";
import * as expense from '../reducers/expense.reducers';


export const selectExpense = createFeatureSelector<expense.State>('expense');

export const selectExpenses = createSelector(
    selectExpense,
    (state: expense.State) => state.expenses
);

export const selectHasError = createSelector(
    selectExpense,
    (state: expense.State) => state.hasError
);

export const selectErrorMessage = createSelector(
    selectExpense,
    (state: expense.State) => state.errorMessage
);
