import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FieldErrorDisplayComponent} from "./field-error-display.component";
import {ErrorDisplayComponent} from "./error-display/error-display.component";

@NgModule({
  declarations: [
    FieldErrorDisplayComponent,
    ErrorDisplayComponent
  ],
  exports: [
    FieldErrorDisplayComponent,
    ErrorDisplayComponent
  ],
  imports: [
    CommonModule
  ]
})
export class FieldErrorDisplayModule { }
