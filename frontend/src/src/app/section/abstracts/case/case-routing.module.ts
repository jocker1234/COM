import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CaseAbstractCreateComponent} from "./case-abstract-create/case-abstract-create.component";

const routes: Routes = [
  {
    path: 'new',
    component: CaseAbstractCreateComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CaseRoutingModule { }
