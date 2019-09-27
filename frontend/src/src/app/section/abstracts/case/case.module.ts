import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CaseRoutingModule} from './case-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {CaseAbstractCreateComponent} from './case-abstract-create/case-abstract-create.component';
import {httpInterceptorProvider} from "../../auth/auth-interceptor";
import {CaseAbstractItemsDetailsComponent} from "./case-abstract-items-details/case-abstract-items-details.component";

@NgModule({
  declarations: [
    CaseAbstractCreateComponent,
    CaseAbstractItemsDetailsComponent
  ],
  imports: [
    CommonModule,
    CaseRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [httpInterceptorProvider],
})
export class CaseModule {
}
