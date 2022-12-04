import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { Router } from '@angular/router';
import { Actions, createEffect, ofType } from '@ngrx/effects';


import { AuthService } from '../../services/auth.service';
import { map, catchError, exhaustMap, of, tap } from 'rxjs';
import { AppState } from '../app.state';
import { getToken, getTokenFail, getTokenSuccess, register, registerFail, registerSuccess } from '../actions/auth.actions';


@Injectable()
export class AuthEffects {

  constructor(
    private actions$: Actions,
    private authService: AuthService,
    private store: Store<AppState>,
    private router: Router
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
            console.log(data)
            this.store.dispatch(getToken({email, password}))
            return registerSuccess();
          }),
          catchError((error) => {
            console.log(error);
            return of(registerFail({ error }));
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
          catchError((error) => {
            console.log(error);
            return of(getTokenFail({ error }))
          })
        )}
      )
    )
  })

  getTokenSuccess$ = createEffect(() =>
    this.actions$.pipe(
      ofType(getTokenSuccess),
      tap(() => {
        console.log('navigating')
        return this.router.navigateByUrl('/home')
      })
    ), { dispatch: false }
  )

}