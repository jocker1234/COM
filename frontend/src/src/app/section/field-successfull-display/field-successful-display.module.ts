import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FieldSuccessfullDisplayComponent} from "./field-successfull-display.component";
import {SuccessfullDisplayComponent} from "./successfull-display/successfull-display.component";



@NgModule({
  declarations: [
    FieldSuccessfullDisplayComponent,
    SuccessfullDisplayComponent
  ],
  exports: [
    FieldSuccessfullDisplayComponent,
    SuccessfullDisplayComponent
  ],
  imports: [
    CommonModule
  ]
})
export class FieldSuccessfulDisplayModule { }
