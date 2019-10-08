import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "../home/home.component";

const routes: Routes = [
  {
    path: 'abstract',
    loadChildren: () => import('./admin-abstracts/admin-abstracts.module').then(value => value.AdminAbstractsModule)
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
