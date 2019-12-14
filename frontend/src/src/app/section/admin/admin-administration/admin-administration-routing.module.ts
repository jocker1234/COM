import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DictionaryEditComponent} from "./Dictionary/dictionary-edit/dictionary-edit.component";
import {DropDataComponent} from "./drop-data/drop-data.component";

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
  {
    path: 'clearData',
    component: DropDataComponent,
    data: {
      breadcrumb: 'Data cleaning',
      title: 'Data cleaning',
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminAdministrationRoutingModule { }
