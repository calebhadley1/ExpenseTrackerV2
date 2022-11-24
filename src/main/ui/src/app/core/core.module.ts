import { NgModule } from '@angular/core';
import { NavbarComponent } from './navbar/navbar.component';
import { MaterialDesignModule } from '../material-design.module';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

// Module containing core functionality of the application 
@NgModule({
  declarations: [
    NavbarComponent
  ],
  exports: [
    NavbarComponent
  ],
  imports: [
    CommonModule,
    MaterialDesignModule,
    RouterModule
  ],
  bootstrap: [NavbarComponent],
})
export class CoreModule { }
