import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {AbstractsComponent} from "./abstracts.component";
import {PasswordResetComponent} from "../login/password-reset/password-reset.component";

const routes: Routes = [
  {
    path: '',
    component: AbstractsComponent,
    data: {
      breadcrumb: 'Abstracts List',
      title: 'Abstracts List',
    },
  },
  {
    path: 'case',
    loadChildren: () => import('./case/case.module').then(value => value.CaseModule),
    data: {
      breadcrumb: 'Case'
    },
  },
  {
    path: 'research',
    loadChildren: () => import('./research/research.module').then(value => value.ResearchModule),
    data: {
      breadcrumb: 'Research'
    },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AbstractsRoutingModule { }
