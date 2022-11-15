import { NgModule } from '@angular/core';
import { NavbarComponent } from './navbar/navbar.component';
import { MaterialDesignModule } from '../material-design.module';
import { CommonModule } from '@angular/common';

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
    MaterialDesignModule
  ],
  bootstrap: [NavbarComponent],
})
export class CoreModule { }
