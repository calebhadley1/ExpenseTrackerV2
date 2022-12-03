import { Injectable } from '@angular/core';
import { Action, Store } from '@ngrx/store';
import { Router } from '@angular/router';
import { Actions, createEffect, ofType } from '@ngrx/effects';


import { AuthService } from '../../services/auth.service';
import { mergeMap, map, catchError, EMPTY, exhaustMap, of, Observable } from 'rxjs';
import { AppState } from '../app.state';
import { getToken, getTokenFail, getTokenSuccess, register, registerFail, registerSuccess } from '../actions/auth.actions';
import { setErrorMessage, setLoadingSpinner } from '../actions/shared.actions';


@Injectable()
export class AuthEffects {

  constructor(
    private actions$: Actions,
    private authService: AuthService,
    private store: Store<AppState>,
  ) {}

  register$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(register),
      exhaustMap((action) => {
        console.log('register$')
        console.log(action.user)
        const email = action.user.email;
        const password = action.user.password;
         return this.authService.register(action.user)
         .pipe(
            map((data) => {
              // this.store.dispatch(setLoadingSpinner({ status: false }));
              console.log(data)
              this.store.dispatch(getToken({email, password}))
              return registerSuccess();
            }),
            catchError((errResp) => {
              // this.store.dispatch(setLoadingSpinner({ status: false }));
              console.log(errResp);
              return of(registerFail());
            })
        );
      })
    );
  });

  getToken$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(getToken),
      exhaustMap((action) => {
        console.log('gettoken')
        return this.authService.token(action.email, action.password)
        .pipe(
          map((token) => {
            console.log('success')
            return getTokenSuccess({ token })
          }),
          catchError((error) => of(getTokenFail({ error })))
        )}
      )
    )
  })


}