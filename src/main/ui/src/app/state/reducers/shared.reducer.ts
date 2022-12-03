import { Action, createReducer, on } from '@ngrx/store';
import { setErrorMessage, setLoadingSpinner } from '../actions/shared.actions';
import { initialState, State } from './auth.reducers';

const _sharedReducer = createReducer(
  initialState,
  on(setLoadingSpinner, (state, action) => {
    return {
      ...state,
      showLoading: action.status,
    };
  }),
  on(setErrorMessage, (state, action) => {
    return {
      ...state,
      errorMessage: action.message,
    };
  })
);

export function SharedReducer(state: State, action: Action) {
  return _sharedReducer(state, action);
}