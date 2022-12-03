import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroupDirective, NgForm, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { Store } from '@ngrx/store';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/shared/models/user';
import { getToken, register } from 'src/app/state/actions/auth.actions';
import { setLoadingSpinner } from 'src/app/state/actions/shared.actions';
import { AppState } from 'src/app/state/app.state';

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
export class LoginFormComponent {

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

  constructor(private fb: FormBuilder, private authService: AuthService, private store: Store<AppState>) { }

  login(): void {
    if(this.loginFormGroup.valid){
      console.log(this.loginFormGroup.value)
      const form = this.loginFormGroup.value;
      const credentials = {
        email: form.emailFormControl || '',
        password: form.passwordFormGroup?.passwordFormControl || ''
      }
      this.store.dispatch(getToken(credentials));
    }
  }
}
