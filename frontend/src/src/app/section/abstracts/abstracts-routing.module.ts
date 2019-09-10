import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {AbstractsComponent} from "./abstracts.component";

const routes: Routes = [
  {
    path: '',
    component: AbstractsComponent
  },
  {
    path: 'case',
    loadChildren: () => import('./case/case.module').then(value => value.CaseModule)
  },
  {
    path: 'research',
    loadChildren: () => import('./research/research.module').then(value => value.ResearchModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AbstractsRoutingModule { }
