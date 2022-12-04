import { createAction, props } from '@ngrx/store';
import { User } from '../../shared/models/user';

//Register

export const REGISTER = '[Auth Page] Register';
export const REGISTER_SUCCESS = '[Auth Page] Register Success';
export const REGISTER_FAIL = '[Auth Page] Register Fail';

export const register = createAction(
  REGISTER,
  props<{ user: User }>()
);

export const registerSuccess = createAction(
  REGISTER_SUCCESS,
);

export const registerFail = createAction(
  REGISTER_FAIL,
  props<{error: any}>()
);

//Get Token

export const GET_TOKEN = '[Auth Page] Get Token';
export const GET_TOKEN_SUCCESS = '[Auth Page] Get Token Success';
export const GET_TOKEN_FAIL = '[Auth Page] Get Token Fail';

export const getToken = createAction(
  GET_TOKEN,
  props<{ email: string, password: string }>()
);

export const getTokenSuccess = createAction(
  GET_TOKEN_SUCCESS,
  props<{ token: string }>()
);

export const getTokenFail = createAction(
  GET_TOKEN_FAIL,
  props<{error: any}>()
);