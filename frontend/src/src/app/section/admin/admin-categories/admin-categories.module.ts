import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminCategoriesRoutingModule } from './admin-categories-routing.module';
import {CategoryListComponent} from "./category-list/category-list.component";


@NgModule({
  declarations: [
    CategoryListComponent
  ],
  imports: [
    CommonModule,
    AdminCategoriesRoutingModule
  ]
})
export class AdminCategoriesModule { }
