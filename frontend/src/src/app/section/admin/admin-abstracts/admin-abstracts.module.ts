import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {AdminAbstractsComponent} from "./admin-abstracts.component";
import {AdminAbstractActionButtonComponent} from "./admin-abstract-action-button/admin-abstract-action-button.component";
import {AdminAbstractsRoutingModule} from "./admin-abstracts-routing.module";
import {AdminModule} from "../admin.module";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AbstractsExportComponent } from './abstracts-export/abstracts-export.component';
import {FieldErrorDisplayModule} from "../../field-error-display/field-error-display.module";
import {FieldSuccessfulDisplayModule} from "../../field-successfull-display/field-successful-display.module";

@NgModule({
  declarations: [
    AdminAbstractsComponent,
    AdminAbstractActionButtonComponent,
    AbstractsExportComponent,
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
    FieldErrorDisplayModule,
    FieldSuccessfulDisplayModule
  ]
})
export class AdminAbstractsModule { }
