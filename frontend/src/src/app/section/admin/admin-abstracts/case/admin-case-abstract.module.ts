import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminCaseAbstractRoutingModule } from './admin-case-abstract-routing.module';
import {AdminCaseAbstractDetailsComponent} from "./admin-case-abstract-details/admin-case-abstract-details.component";
import {AdminAbstractsModule} from "../admin-abstracts.module";


@NgModule({
  declarations: [
    AdminCaseAbstractDetailsComponent,
  ],
  imports: [
    CommonModule,
    AdminCaseAbstractRoutingModule,
    AdminAbstractsModule,
  ]
})
export class AdminCaseAbstractModule { }
