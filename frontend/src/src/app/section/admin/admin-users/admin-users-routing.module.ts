import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UsersListComponent} from "./users-list/users-list.component";
import {AdminUserDetailsComponent} from "./admin-user-details/admin-user-details.component";
import {SingleSendMailComponent} from "./single-send-mail/single-send-mail.component";
import {MultiSendMailComponent} from "./multi-send-mail/multi-send-mail.component";
import {CheckMultiUserToMailComponent} from "./check-multi-user-to-mail/check-multi-user-to-mail.component";


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
    path: 'group-mail',
    component: CheckMultiUserToMailComponent,
    data: {
      breadcrumb: 'Check Users To Group Mail',
      title: 'Check Users To Group Mail',
    },
  },
  {
    path: 'group-mail/send',
    component: MultiSendMailComponent,
    data: {
      breadcrumb: 'Send Group Mail',
      title: 'Send Group Mail',
    },
  },
  {
    path: 'export',//TODO
    component: MultiSendMailComponent,
    data: {
      breadcrumb: 'Export User',
      title: 'Export User',
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
