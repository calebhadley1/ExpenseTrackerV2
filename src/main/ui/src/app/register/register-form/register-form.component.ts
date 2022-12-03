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

export const confirmPasswordValidator: ValidatorFn = (control: AbstractControl): 
ValidationErrors | null => {
    const password = control.get('passwordFormControl');
    const confirmPassword = control.get('confirmPasswordFormControl');
    const equal = password && confirmPassword && password.value === confirmPassword.value
    !equal && confirmPassword ? confirmPassword.setErrors({passwordsMatch: equal}) : null;
    return !equal ? {passwordsMatch: equal} : null;
};

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent {

  registerFormGroup = this.fb.group({
    firstNameFormControl: ['', [Validators.required,]],
    lastNameFormControl: ['', [Validators.required,]],
    emailFormControl: ['', [Validators.required, Validators.email]],
    passwordFormGroup: this.fb.group({
      passwordFormControl: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(20),
        Validators.pattern(/[A-Z]/), // Contains a capital letter
        Validators.pattern(/[a-z]/), // Contains a lowercase letter
        Validators.pattern(/[\d]/) // Contains a number
      ]],
      confirmPasswordFormControl: ['', [Validators.required]]
    }, {validators: confirmPasswordValidator})
  });

  matcher = new MyErrorStateMatcher();
  passwordMatcher = new MyErrorStateMatcher();
  confirmPasswordMatcher = new MyErrorStateMatcher();

  constructor(private fb: FormBuilder, private authService: AuthService, private store: Store<AppState>) { }

  register(): void {
    if(this.registerFormGroup.valid){
      console.log(this.registerFormGroup.value)
      const form = this.registerFormGroup.value;
      const user: User = {
        firstName: form.firstNameFormControl || '',
        lastName: form.lastNameFormControl || '',
        email: form.emailFormControl || '',
        password: form.passwordFormGroup?.passwordFormControl || ''
      }
      this.store.dispatch(register({user}));
    }
  }
}
