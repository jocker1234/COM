import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'dictionary',
    loadChildren: () => import('./Dictionary/dictionary.module').then(value => value.DictionaryModule),
    data: {
      breadcrumb: 'Dictionary'
    },
  },
  {
    path: 'admins',
    loadChildren: () => import('./administrator/administrator.module').then(value => value.AdministratorModule),
    data: {
      breadcrumb: 'Administrator'
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminAdministrationRoutingModule { }
