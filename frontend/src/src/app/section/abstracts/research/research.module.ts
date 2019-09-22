import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResearchRoutingModule } from './research-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {ResearchAbstractCreateComponent} from "./research-abstract-create/research-abstract-create.component";

@NgModule({
  declarations: [
    ResearchAbstractCreateComponent
  ],
  imports: [
    CommonModule,
    ResearchRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule
  ]
})
export class ResearchModule { }
