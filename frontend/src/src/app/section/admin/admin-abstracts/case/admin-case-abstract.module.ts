import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminCaseAbstractRoutingModule } from './admin-case-abstract-routing.module';
import {AdminCaseAbstractDetailsComponent} from "./admin-case-abstract-details/admin-case-abstract-details.component";
import {AdminAbstractsModule} from "../admin-abstracts.module";
import {FieldErrorDisplayModule} from "../../../field-error-display/field-error-display.module";


@NgModule({
  declarations: [
    AdminCaseAbstractDetailsComponent,
  ],
  imports: [
    CommonModule,
    AdminCaseAbstractRoutingModule,
    AdminAbstractsModule,
    FieldErrorDisplayModule,
  ]
})
export class AdminCaseAbstractModule { }
