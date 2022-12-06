import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { MaterialDesignModule } from '../material-design.module';
import { CoreModule } from '../core/core.module';
import { HomeTransactionTableComponent } from './home-transaction-table/home-transaction-table.component';


@NgModule({
  declarations: [
    HomeComponent,
    HomeTransactionTableComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    HomeRoutingModule,
    MaterialDesignModule
  ]
})
export class HomeModule { }
