import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { MaterialDesignModule } from '../material-design.module';
import { CoreModule } from '../core/core.module';


@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    HomeRoutingModule,
    MaterialDesignModule
  ]
})
export class HomeModule { }
