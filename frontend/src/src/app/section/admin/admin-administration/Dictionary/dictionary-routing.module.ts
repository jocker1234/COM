import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DictionaryListComponent} from "./dictionary-list/dictionary-list.component";
import {DictionaryDetailsComponent} from "./dictionary-details/dictionary-details.component";
import {DictionaryEditComponent} from "./dictionary-edit/dictionary-edit.component";

const routes: Routes = [
  {
    path: '',
    component: DictionaryListComponent,
    data: {
      breadcrumb: 'Dictionary List'
    },
  },
  {
    path: ':id',
    component: DictionaryDetailsComponent,
    data: {
      breadcrumb: 'Dictionary Details'
    },
  },
  {
    path: ':id/edit',
    component: DictionaryEditComponent,
    data: {
      breadcrumb: 'Dictionary Edit'
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DictionaryRoutingModule { }
