import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CaseRoutingModule} from './case-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {CaseAbstractCreateComponent} from './case-abstract-create/case-abstract-create.component';
import {httpInterceptorProvider} from "../../auth/auth-interceptor";

@NgModule({
  declarations: [
    CaseAbstractCreateComponent
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
