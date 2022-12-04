import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { OnInitEffects } from '@ngrx/effects';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Credentials } from 'src/app/shared/models/user';
import { resetErrorState } from 'src/app/state/actions/auth.actions';
import { AppState } from 'src/app/state/app.state';
import { selectAuthErrorMessage, selectAuthHasError } from 'src/app/state/selectors/auth.selector';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {
  @Output() loginEvent = new EventEmitter<Credentials>();
  hasError$: Observable<boolean> = this.store.select(selectAuthHasError);
  errorMsg$: Observable<string> = this.store.select(selectAuthErrorMessage);

  loginFormGroup = this.fb.group({
    emailFormControl: ['', 
      [Validators.required]],
    passwordFormGroup: this.fb.group({
      passwordFormControl: ['', [
        Validators.required,
      ]],
    })
  });

  matcher = new MyErrorStateMatcher();
  passwordMatcher = new MyErrorStateMatcher();

  constructor(private fb: FormBuilder, private store: Store<AppState>) { }

  ngOnInit(): void {
    this.resetErrorMsg();
  }

  login(): void {
    if(this.loginFormGroup.valid){
      console.log(this.loginFormGroup.value)
      const form = this.loginFormGroup.value;
      const credentials: Credentials = {
        email: form.emailFormControl || '',
        password: form.passwordFormGroup?.passwordFormControl || ''
      }
      this.loginEvent.emit(credentials)
    }
  }

  resetErrorMsg(): void {
    this.loginFormGroup.valueChanges.subscribe(() => {
      this.store.dispatch(resetErrorState())
    })
  }
}
