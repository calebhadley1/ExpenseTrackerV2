import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';


// Module containing core functionality of the application 
@NgModule({
  declarations: [
    NavbarComponent
  ],
  imports: [
    CommonModule
  ]
})
export class CoreModule { }
