import { createActionGroup, emptyProps, props } from "@ngrx/store";
import { Expense, ExpenseRequest } from "src/app/shared/models/expense";

//Get
export const GET_ALL_EXPENSES = '[Expense] Get All Expenses';
export const GET_ALL_EXPENSES_SUCCESS = '[Expense] Get All Expenses Success';
export const GET_ALL_EXPENSES_FAIL = '[Expense] Get All Expenses Fail';

//Get by Id
export const GET_EXPENSE_BY_ID = '[Expense] Get Expense By ID';
export const GET_EXPENSE_BY_ID_SUCCESS = '[Expense] Get Expense By ID Success';
export const GET_EXPENSE_BY_ID_FAIL = '[Expense] Get Expense By ID Fail';

//Add
export const ADD_EXPENSE = '[Expense] Add Expense';
export const ADD_EXPENSE_SUCCESS = '[Expense] Add Expense Success';
export const ADD_EXPENSE_FAIL = '[Expense] Add Expense Fail';

//Delete
export const DELETE_EXPENSE = '[Expense] Delete Expense';
export const DELETE_EXPENSE_SUCCESS = '[Expense] Delete Expense Success';
export const DELETE_EXPENSE_FAIL = '[Expense] Delete Expense Fail';

//Update
export const UPDATE_EXPENSE = '[Expense] Update Expense';
export const UPDATE_EXPENSE_SUCCESS = '[Expense] Update Expense Success';
export const UPDATE_EXPENSE_FAIL = '[Expense] Update Expense Fail';

export const ExpensesApiActions = createActionGroup({
    source: 'Expenses API',
    events: {
        GET_ALL_EXPENSES: emptyProps(),
        GET_ALL_EXPENSES_SUCCESS: props<{ expenses: ReadonlyArray<Expense> }>(),
        GET_ALL_EXPENSES_FAIL: props<{ error: any }>(),
        
        GET_EXPENSE_BY_ID: props<{ id: number }>(),
        GET_EXPENSE_BY_ID_SUCCESS: props<{ expense: Readonly<Expense> }>(),
        GET_EXPENSE_BY_ID_FAIL: props<{ error: any }>(),

        ADD_EXPENSE: props<{ expense: ExpenseRequest }>(),
        ADD_EXPENSE_SUCCESS: emptyProps(),
        ADD_EXPENSE_FAIL: props<{ error: any }>(),

        DELETE_EXPENSE: props<{ id: number }>(),
        DELETE_EXPENSE_SUCCESS: emptyProps(),
        DELETE_EXPENSE_FAIL: props<{ error: any }>(),

        UPDATE_EXPENSE: props<{ expense: ExpenseRequest }>(),
        UPDATE_EXPENSE_SUCCESS: props<{ expense: Expense }>(),
        UPDATE_EXPENSE_FAIL: props<{ error: any }>(),
    },
});
