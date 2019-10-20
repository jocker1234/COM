import {NgModule} from "@angular/core";
import {AbstractsComponent} from "./abstracts.component";
import {CommonModule} from "@angular/common";
import {AbstractsRoutingModule} from "./abstracts-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {httpInterceptorProvider} from "../auth/auth-interceptor";
import { ChooseNewAbstractContentComponent } from './choose-new-abstract-content/choose-new-abstract-content.component';
import { AbstractActionButtonsComponent } from './abstract-action-buttons/abstract-action-buttons.component';
import { AbstractsTableComponent } from './abstracts-table/abstracts-table.component';

@NgModule({
  declarations: [
    AbstractsComponent,
    ChooseNewAbstractContentComponent,
    AbstractActionButtonsComponent,
    AbstractsTableComponent
  ],
  imports: [
    CommonModule,
    AbstractsRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
  ],
  entryComponents: [
    ChooseNewAbstractContentComponent
  ],
  providers: [httpInterceptorProvider],
  exports: [
    AbstractActionButtonsComponent
  ]
})
export class AbstractsModule { }
