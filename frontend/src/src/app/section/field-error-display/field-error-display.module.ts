import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FieldErrorDisplayComponent} from "./field-error-display.component";

@NgModule({
  declarations: [
    FieldErrorDisplayComponent
  ],
  exports: [
    FieldErrorDisplayComponent
  ],
  imports: [
    CommonModule
  ]
})
export class FieldErrorDisplayModule { }
