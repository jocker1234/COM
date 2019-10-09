import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AdminRoutingModule} from './admin-routing.module';
import {AdminAbstractsModule} from "./admin-abstracts/admin-abstracts.module";


@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    AdminAbstractsModule
  ]
})
export class AdminModule {
}
