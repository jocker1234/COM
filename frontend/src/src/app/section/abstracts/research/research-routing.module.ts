import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ResearchAbstractCreateComponent} from "./research-abstract-create/research-abstract-create.component";
import {ResearchAbstractItemsDetailsComponent} from "./research-abstract-items-details/research-abstract-items-details.component";
import {ResearchAbstractEditComponent} from "./research-abstract-edit/research-abstract-edit.component";

const routes: Routes = [
  {
    path: 'new',
    component: ResearchAbstractCreateComponent
  },
  {
    path: ':id',
    component: ResearchAbstractItemsDetailsComponent
  },
  {
    path: ':id/edit',
    component: ResearchAbstractEditComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResearchRoutingModule { }
