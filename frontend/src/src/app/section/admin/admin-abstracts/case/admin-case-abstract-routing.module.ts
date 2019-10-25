import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminCaseAbstractDetailsComponent} from "./admin-case-abstract-details/admin-case-abstract-details.component";


const routes: Routes = [
  {
    path: ':id',
    component:AdminCaseAbstractDetailsComponent,
    data: {
      breadcrumb: 'Case Abstract Details'
    },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminCaseAbstractRoutingModule { }
