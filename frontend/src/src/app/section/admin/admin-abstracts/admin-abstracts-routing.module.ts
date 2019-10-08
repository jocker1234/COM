import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminAbstractsComponent} from "./admin-abstracts.component";


const routes: Routes = [
  {
    path: '',
    component: AdminAbstractsComponent
  },
  {
    path: 'case',
    loadChildren: () => import('./case/admin-case-abstract.module').then(value => value.AdminCaseAbstractModule)
  },
  {
    path: 'research',
    loadChildren: () => import('./research/admin-research-abstract.module').then(value => value.AdminResearchAbstractModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminAbstractsRoutingModule { }
