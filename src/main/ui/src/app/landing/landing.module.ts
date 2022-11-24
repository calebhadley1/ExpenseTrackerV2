import { NgModule } from '@angular/core';

import { LandingRoutingModule } from './landing-routing.module';
import { LandingComponent } from './landing.component';
import { CoreModule } from '../core/core.module';
import { MaterialDesignModule } from '../material-design.module';


@NgModule({
  declarations: [
    LandingComponent
  ],
  exports: [
    LandingComponent
  ],
  imports: [
    CoreModule,
    LandingRoutingModule,
    MaterialDesignModule,
  ]
})
export class LandingModule { }
