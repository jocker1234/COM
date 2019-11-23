import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ResearchAbstractCreateComponent} from "./research-abstract-create/research-abstract-create.component";
import {ResearchAbstractItemsDetailsComponent} from "./research-abstract-items-details/research-abstract-items-details.component";
import {ResearchAbstractEditComponent} from "./research-abstract-edit/research-abstract-edit.component";
import {PasswordResetComponent} from "../../login/password-reset/password-reset.component";

const routes: Routes = [
  {
    path: 'new',
    component: ResearchAbstractCreateComponent,
    data: {
      breadcrumb: 'Create Research Abstract',
      title: 'Create Research Abstract',
    },
  },
  {
    path: ':id',
    component: ResearchAbstractItemsDetailsComponent,
    data: {
      breadcrumb: 'Research Abstract Details',
      title: 'Research Abstract Details',
    },
  },
  {
    path: ':id/edit',
    component: ResearchAbstractEditComponent,
    data: {
      breadcrumb: 'Edit Research Abstract',
      title: 'Edit Research Abstract',
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResearchRoutingModule { }
