import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot, UrlSegment, UrlTree } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppState } from '../state/app.state';
import { selectAuthIsAuthenticated } from '../state/selectors/auth.selector';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanLoad {
  isAuthenticated$: Observable<boolean> = this.store.select(selectAuthIsAuthenticated);

  constructor(private store: Store<AppState>, private router: Router) {}

  canLoad(): Observable<boolean> {
    return this.isAuthenticated$
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>{
    return this.isAuthenticated$;
  }

  
}
