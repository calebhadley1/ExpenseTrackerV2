import { createReducer, on } from "@ngrx/store";
import { Expense } from "src/app/shared/models/expense";
import { ExpensesApiActions } from "../actions/expense.actions";

export interface State {
    expenses: Expense[];
    hasError: boolean;
    errorMessage: string;
}
  
export const initialState: State = {
    expenses: [],
    errorMessage: '',
    hasError: false
};

export const expenseReducer = createReducer(
    initialState,
    //Get
    on(ExpensesApiActions.get_all_expenses, (state) => ({
        expenses: [], //maybe should stay same
        errorMessage: '',
        hasError: false
    })),
    on(ExpensesApiActions.get_all_expenses_success, (state) => ({
        expenses: state.expenses,
        errorMessage: '',
        hasError: false
    })),
    on(ExpensesApiActions.get_all_expenses_fail, (state) => ({
        expenses: [],
        errorMessage: 'Error getting all books.',
        hasError: true
    })),

    //ToDo
);
