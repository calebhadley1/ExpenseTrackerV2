import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionsRoutingModule } from './transactions-routing.module';
import { TransactionsComponent } from './transactions.component';
import { CoreModule } from '../core/core.module';
import { MaterialDesignModule } from '../material-design.module';
import { TransactionsTableComponent } from './transactions-table/transactions-table.component';


@NgModule({
  declarations: [
    TransactionsComponent,
    TransactionsTableComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    TransactionsRoutingModule,
    MaterialDesignModule
  ]
})
export class TransactionsModule { }
