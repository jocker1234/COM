import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ResearchAbstractCreateComponent} from "./research-abstract-create/research-abstract-create.component";

const routes: Routes = [
  {
    path: 'new',
    component: ResearchAbstractCreateComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResearchRoutingModule { }
