import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResearchRoutingModule } from './research-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {ResearchAbstractCreateComponent} from "./research-abstract-create/research-abstract-create.component";
import {ResearchAbstractItemsDetailsComponent} from "./research-abstract-items-details/research-abstract-items-details.component";
import { ResearchAbstractEditComponent } from './research-abstract-edit/research-abstract-edit.component';
import {AbstractsModule} from "../abstracts.module";
import {AutosizeModule} from "ngx-autosize";
import {FieldErrorDisplayModule} from "../../field-error-display/field-error-display.module";

@NgModule({
  declarations: [
    ResearchAbstractCreateComponent,
    ResearchAbstractItemsDetailsComponent,
    ResearchAbstractEditComponent
  ],
  imports: [
    CommonModule,
    ResearchRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    AbstractsModule,
    AutosizeModule,
    FieldErrorDisplayModule,
  ]
})
export class ResearchModule { }
