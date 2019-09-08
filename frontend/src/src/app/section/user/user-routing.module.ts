import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UsersListComponent} from "./users-list/users-list.component";
import {AuthGuard} from "../auth/auth-guard";
import {UserListDetailComponent} from "./user-list-detail/user-list-detail.component";
import {UserUpdateComponent} from "./user-update/user-update.component";

const routes: Routes = [
  {
    path: '',
    component: UsersListComponent,
    data: {
      authorities: ['ROLE_ADMIN']
    },
    canActivate: [AuthGuard]
  },
  {
    path: ':id',
    component: UserListDetailComponent
  },
  {
    path: ':id/edit',
    component: UserUpdateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
