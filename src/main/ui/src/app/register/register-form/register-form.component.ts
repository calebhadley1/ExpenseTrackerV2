import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroupDirective, NgForm, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';

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
export class RegisterFormComponent implements OnInit {

  registerFormGroup = this.fb.group({
    firstNameFormControl: [''],
    lastNameFormControl: [''],
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

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
  }

}
