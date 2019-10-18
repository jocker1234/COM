import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AdminUsersRoutingModule} from './admin-users-routing.module';
import {AdminUserDetailsComponent} from './admin-user-details/admin-user-details.component';
import {UsersListComponent} from "./users-list/users-list.component";
import {ButtonUserDetailsComponent} from "./admin-user-details/button-user-details/button-user-details.component";


@NgModule({
  declarations: [
    UsersListComponent,
    AdminUserDetailsComponent,
    ButtonUserDetailsComponent
  ],
  imports: [
    CommonModule,
    AdminUsersRoutingModule
  ]
})
export class AdminUsersModule {
}
