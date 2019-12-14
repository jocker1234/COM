import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AdminAdministrationRoutingModule} from './admin-administration-routing.module';
import {FieldErrorDisplayModule} from "../../field-error-display/field-error-display.module";
import {ReactiveFormsModule} from "@angular/forms";
import { DropDataComponent } from './drop-data/drop-data.component';


@NgModule({
  declarations: [

  DropDataComponent],
  imports: [
    CommonModule,
    AdminAdministrationRoutingModule,
    FieldErrorDisplayModule,
    ReactiveFormsModule,
  ]
})
export class AdminAdministrationModule {
}
