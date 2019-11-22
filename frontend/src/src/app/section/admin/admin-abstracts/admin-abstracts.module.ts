import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {AdminAbstractsComponent} from "./admin-abstracts.component";
import {AdminAbstractActionButtonComponent} from "./admin-abstract-action-button/admin-abstract-action-button.component";
import {AdminAbstractsRoutingModule} from "./admin-abstracts-routing.module";
import {AdminModule} from "../admin.module";

@NgModule({
  declarations: [
    AdminAbstractsComponent,
    AdminAbstractActionButtonComponent,
  ],
  exports: [
    AdminAbstractActionButtonComponent,
  ],
  imports: [
    CommonModule,
    AdminAbstractsRoutingModule,
    AdminModule,
  ]
})
export class AdminAbstractsModule { }
