import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminAbstractsComponent} from "./admin-abstracts.component";
import {PasswordResetComponent} from "../../login/password-reset/password-reset.component";


const routes: Routes = [
  {
    path: '',
    component: AdminAbstractsComponent,
    data: {
      breadcrumb: 'Abstracts List',
      title: 'Abstracts List',
    },
  },
  {
    path: 'case',
    loadChildren: () => import('./case/admin-case-abstract.module').then(value => value.AdminCaseAbstractModule),
    data: {
      breadcrumb: 'Case'
    },
  },
  {
    path: 'research',
    loadChildren: () => import('./research/admin-research-abstract.module').then(value => value.AdminResearchAbstractModule),
    data: {
      breadcrumb: 'Research'
    },
  },
  {
    path: 'export',
    component: AdminAbstractsComponent,
    data: {
      breadcrumb: 'Export Abstracts',
      title: 'Export Abstracts',
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminAbstractsRoutingModule { }
