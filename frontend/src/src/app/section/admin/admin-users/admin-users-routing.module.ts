import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UsersListComponent} from "./users-list/users-list.component";
import {AdminUserDetailsComponent} from "./admin-user-details/admin-user-details.component";
import {SingleSendMailComponent} from "./single-send-mail/single-send-mail.component";


const routes: Routes = [
  {
    path: '',
    component: UsersListComponent,
    data: {
      breadcrumb: 'Users List',
      title: 'Users List',
    },
  },
  {
    path: ':id',
    component: AdminUserDetailsComponent,
    data: {
      breadcrumb: 'User Details',
      title: 'User Details',
    },
  },
  {
    path: ':id/send-mail',
    component: SingleSendMailComponent,
    data: {
      breadcrumb: 'Send Mail',
      title: 'Send Mail',
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminUsersRoutingModule { }
