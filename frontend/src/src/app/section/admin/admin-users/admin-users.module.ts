import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AdminUsersRoutingModule} from './admin-users-routing.module';
import {AdminUserDetailsComponent} from './admin-user-details/admin-user-details.component';
import {UsersListComponent} from "./users-list/users-list.component";
import {ButtonUserDetailsComponent} from "./admin-user-details/button-user-details/button-user-details.component";
import { AdminAbstractsUserComponent } from './admin-user-details/admin-abstracts-user/admin-abstracts-user.component';
import { SingleSendMailComponent } from './single-send-mail/single-send-mail.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FieldErrorDisplayModule} from "../../field-error-display/field-error-display.module";
import {AdminModule} from "../admin.module";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {AutosizeModule} from "ngx-autosize";


@NgModule({
  declarations: [
    UsersListComponent,
    AdminUserDetailsComponent,
    ButtonUserDetailsComponent,
    AdminAbstractsUserComponent,
    SingleSendMailComponent
  ],
  exports: [
    ButtonUserDetailsComponent
  ],
  imports: [
    CommonModule,
    AdminUsersRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    FieldErrorDisplayModule,
    AdminModule,
    FontAwesomeModule,
    AutosizeModule
  ]
})
export class AdminUsersModule {
}
