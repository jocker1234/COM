import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CaseRoutingModule} from './case-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {CaseAbstractCreateComponent} from './case-abstract-create/case-abstract-create.component';
import {httpInterceptorProvider} from "../../auth/auth-interceptor";
import {CaseAbstractItemsDetailsComponent} from "./case-abstract-items-details/case-abstract-items-details.component";
import { CaseAbstractEditComponent } from './case-abstract-edit/case-abstract-edit.component';
import {AbstractsModule} from "../abstracts.module";
import {AutosizeModule} from "ngx-autosize";
import {FieldErrorDisplayModule} from "../../field-error-display/field-error-display.module";

@NgModule({
  declarations: [
    CaseAbstractCreateComponent,
    CaseAbstractItemsDetailsComponent,
    CaseAbstractEditComponent
  ],
  imports: [
    CommonModule,
    CaseRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    AbstractsModule,
    AutosizeModule,
    FieldErrorDisplayModule
  ],
  providers: [httpInterceptorProvider],
})
export class CaseModule {
}
