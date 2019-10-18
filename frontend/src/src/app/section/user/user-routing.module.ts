import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserDetailComponent} from "./user-detail/user-detail.component";
import {UserUpdateComponent} from "./user-update/user-update.component";

const routes: Routes = [
  {
    path: ':id',
    component: UserDetailComponent
  },
  {
    path: ':id/edit',
    component: UserUpdateComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
