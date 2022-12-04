import { createFeatureSelector, createSelector, State } from "@ngrx/store";
import * as auth from '../reducers/auth.reducers';

export const selectAuth = createFeatureSelector<Readonly<auth.State>>('auth');

export const selectAuthIsAuthenticated = createSelector(
    selectAuth,
    (state: auth.State) => state.isAuthenticated
);

export const selectAuthToken = createSelector(
    selectAuth,
    (state: auth.State) => state.token
);

export const selectAuthHasError = createSelector(
    selectAuth,
    (state: auth.State) => state.hasError
);

export const selectAuthErrorMessage = createSelector(
    selectAuth,
    (state: auth.State) => state.errorMessage
);
