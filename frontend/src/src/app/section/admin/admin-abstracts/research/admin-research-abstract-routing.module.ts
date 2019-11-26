import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminResearchAbstractDetailsComponent} from "./admin-research-abstract-details/admin-research-abstract-details.component";
import {PasswordResetComponent} from "../../../login/password-reset/password-reset.component";


const routes: Routes = [
  {
    path: ':id',
    component: AdminResearchAbstractDetailsComponent,
    data: {
      breadcrumb: 'Research Abstract Details',
      title: 'Research Abstract Details',
    },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminResearchAbstractRoutingModule {
}
