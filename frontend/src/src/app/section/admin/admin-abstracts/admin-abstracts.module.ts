import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {AdminAbstractsComponent} from "./admin-abstracts.component";
import {AdminAbstractActionButtonComponent} from "./admin-abstract-action-button/admin-abstract-action-button.component";
import {AdminAbstractsRoutingModule} from "./admin-abstracts-routing.module";
import {AdminModule} from "../admin.module";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

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
    FontAwesomeModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class AdminAbstractsModule { }
