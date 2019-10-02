import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CaseAbstractCreateComponent} from "./case-abstract-create/case-abstract-create.component";
import {CaseAbstractItemsDetailsComponent} from "./case-abstract-items-details/case-abstract-items-details.component";
import {CaseAbstractEditComponent} from "./case-abstract-edit/case-abstract-edit.component";

const routes: Routes = [
  {
    path: 'new',
    component: CaseAbstractCreateComponent
  },
  {
    path: ':id',
    component: CaseAbstractItemsDetailsComponent
  },
  {
    path: ':id/edit',
    component: CaseAbstractEditComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CaseRoutingModule { }
