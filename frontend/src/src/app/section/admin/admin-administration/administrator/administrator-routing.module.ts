import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdministratorListComponent} from "./administrator-list/administrator-list.component";
import {AdministratorDetailComponent} from "./administrator-detail/administrator-detail.component";
import {AdministratorCreateComponent} from "./administrator-create/administrator-create.component";

const routes: Routes = [
  {
    path: '',
    component: AdministratorListComponent,
    data: {
      breadcrumb: 'Administrator List',
      title: 'Administrator List',
    },
  },
  {
    path: 'new',
    component: AdministratorCreateComponent,
    data: {
      breadcrumb: 'Administrator Create',
      title: 'Administrator Create',
    },
  },
  {
    path: ':id',
    component: AdministratorDetailComponent,
    data: {
      breadcrumb: 'Administrator Details',
      title: 'Administrator Details',
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministratorRoutingModule { }
