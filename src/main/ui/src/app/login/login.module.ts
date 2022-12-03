import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { CoreModule } from '../core/core.module';
import { LoginFormComponent } from './login-form/login-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialDesignModule } from '../material-design.module';


@NgModule({
  declarations: [
    LoginComponent,
    LoginFormComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    LoginRoutingModule,
    MaterialDesignModule,
    ReactiveFormsModule,
  ]
})
export class LoginModule { }
