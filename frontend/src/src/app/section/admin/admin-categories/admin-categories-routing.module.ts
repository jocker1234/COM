import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CategoryListComponent} from "./category-list/category-list.component";


const routes: Routes = [
  {
    path: '',
    component: CategoryListComponent,
    data: {
      breadcrumb: 'Category list',
      title: 'Category list',
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminCategoriesRoutingModule { }
