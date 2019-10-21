import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UserRoutingModule} from './user-routing.module';
import {UserDetailComponent} from "./user-detail/user-detail.component";
import {UserUpdateComponent} from "./user-update/user-update.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {FieldErrorDisplayModule} from "../field-error-display/field-error-display.module";
import { ButtonUserDetailsComponent } from './user-detail/button-user-details/button-user-details.component';

@NgModule({
  declarations: [
    UserDetailComponent,
    UserUpdateComponent,
    ButtonUserDetailsComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    FieldErrorDisplayModule
  ]
})
export class UserModule {
}
