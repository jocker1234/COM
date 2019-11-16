import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminResearchAbstractRoutingModule } from './admin-research-abstract-routing.module';
import { AdminResearchAbstractDetailsComponent } from './admin-research-abstract-details/admin-research-abstract-details.component';
import {AdminAbstractsModule} from "../admin-abstracts.module";
import {FieldErrorDisplayModule} from "../../../field-error-display/field-error-display.module";


@NgModule({
  declarations: [
    AdminResearchAbstractDetailsComponent,
  ],
  imports: [
    CommonModule,
    AdminResearchAbstractRoutingModule,
    AdminAbstractsModule,
    FieldErrorDisplayModule,
  ]
})
export class AdminResearchAbstractModule { }
