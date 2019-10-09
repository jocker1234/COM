import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminResearchAbstractDetailsComponent} from "./admin-research-abstract-details/admin-research-abstract-details.component";


const routes: Routes = [
  {
    path: ':id',
    component: AdminResearchAbstractDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminResearchAbstractRoutingModule {
}
