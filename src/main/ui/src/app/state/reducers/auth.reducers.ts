import { createReducer, on } from "@ngrx/store";
import { Token } from "src/app/shared/models/token";
import { getToken, getTokenFail, getTokenSuccess, register, registerFail, registerSuccess } from "../actions/auth.actions";

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

  on(getToken, (state) => ({
    isAuthenticated: false,
    token: { token: '' },
    errorMessage: '',
    hasError: false
  })),

  on(getTokenSuccess, (state) => ({
    isAuthenticated: true,
    token: { token: state.token.token },
    errorMessage: '',
    hasError: false
  })),

  on(getTokenFail, (state) => ({
    isAuthenticated: false,
    token: { token: '' },
    errorMessage: 'Error logging in. Please try again later.',
    hasError: true
  })),

);