import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { Credentials } from 'src/app/shared/models/user';

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
  @Output() loginEvent = new EventEmitter<Credentials>();

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

  constructor(private fb: FormBuilder) { }

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
}
