import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import {UserListDetailComponent} from "./user-list-detail/user-list-detail.component";
import {UsersListComponent} from "./users-list/users-list.component";
import {UserUpdateComponent} from "./user-update/user-update.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  declarations: [
    UserListDetailComponent,
    UsersListComponent,
    UserUpdateComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule
  ]
})
export class UserModule { }
