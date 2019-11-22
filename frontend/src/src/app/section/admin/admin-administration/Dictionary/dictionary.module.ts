import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DictionaryRoutingModule } from './dictionary-routing.module';
import {DictionaryListComponent} from "./dictionary-list/dictionary-list.component";
import {DictionaryDetailsComponent} from "./dictionary-details/dictionary-details.component";
import {DictionaryEditComponent} from "./dictionary-edit/dictionary-edit.component";
import {FieldErrorDisplayModule} from "../../../field-error-display/field-error-display.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    DictionaryListComponent,
    DictionaryDetailsComponent,
    DictionaryEditComponent
  ],
  imports: [
    CommonModule,
    DictionaryRoutingModule,
    FieldErrorDisplayModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class DictionaryModule { }
