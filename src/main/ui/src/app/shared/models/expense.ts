export interface Expense {
    id: number,
    name: string,
    description: string,
    amount: number,
    appUserId: number
}

export interface ExpenseRequest {
    name: string,
    description: string,
    amount: number,
}