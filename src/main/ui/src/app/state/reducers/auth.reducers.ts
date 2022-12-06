import { createReducer, on } from "@ngrx/store";
import { Token } from "src/app/shared/models/token";
import { getToken, getTokenFail, getTokenSuccess, register, registerFail, registerSuccess, resetErrorState, RESET_ERROR_STATE } from "../actions/auth.actions";

export interface State {
  /*
  user: User;
  isLoading: boolean;
  */
  isAuthenticated: boolean;
  token: Token;
  hasError: boolean;
  errorMessage: string;
}

export const initialState: State = {
  isAuthenticated: false,
  token: { token: '' },
  errorMessage: '',
  hasError: false
};

export const authReducer = createReducer(
  initialState,

  // Register

  on(register, (state) => ({
    isAuthenticated: false,
    token: { token: '' },
    errorMessage: '',
    hasError: false,
  })),

  on(registerSuccess, (state) => ({
    ...state,
    errorMessage: '',
    hasError: false
  })),

  on(registerFail, (state) => ({
    ...state,
    errorMessage: 'Error registering. Please try again later',
    hasError: true
  })),

  // Get token

  on(getToken, (state) => ({
    isAuthenticated: false,
    token: { token: '' },
    errorMessage: '',
    hasError: false
  })),

  on(getTokenSuccess, (state, { token }) => ({
    isAuthenticated: true,
    token: token,
    errorMessage: '',
    hasError: false
  })),

  on(getTokenFail, (state) => ({
    isAuthenticated: false,
    token: { token: '' },
    errorMessage: 'Error logging in. Please try again later.',
    hasError: true
  })),

  // Utility

  on(resetErrorState, (state) => ({
    ...state,
    errorMessage: '',
    hasError: false
  }))

);