import {NgModule} from "@angular/core";
import {AbstractsComponent} from "./abstracts.component";
import {CommonModule} from "@angular/common";
import {AbstractsRoutingModule} from "./abstracts-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {httpInterceptorProvider} from "../auth/auth-interceptor";

@NgModule({
  declarations: [
    AbstractsComponent
  ],
  imports: [
    CommonModule,
    AbstractsRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
  ],
  providers: [httpInterceptorProvider],
})
export class AbstractsModule { }
