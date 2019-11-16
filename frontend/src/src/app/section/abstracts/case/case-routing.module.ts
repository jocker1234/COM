import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CaseAbstractCreateComponent} from "./case-abstract-create/case-abstract-create.component";
import {CaseAbstractItemsDetailsComponent} from "./case-abstract-items-details/case-abstract-items-details.component";
import {CaseAbstractEditComponent} from "./case-abstract-edit/case-abstract-edit.component";

const routes: Routes = [
  {
    path: 'new',
    component: CaseAbstractCreateComponent,
    data: {
      breadcrumb: 'Create Case Report'
    },
  },
  {
    path: ':id',
    component: CaseAbstractItemsDetailsComponent,
    data: {
      breadcrumb: 'Case Report Details'
    },
  },
  {
    path: ':id/edit',
    component: CaseAbstractEditComponent,
    data: {
      breadcrumb: 'Edit Case Report'
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CaseRoutingModule { }
