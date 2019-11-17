import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminCategoriesRoutingModule } from './admin-categories-routing.module';
import {CategoryListComponent} from "./category-list/category-list.component";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    CategoryListComponent
  ],
  imports: [
    CommonModule,
    AdminCategoriesRoutingModule,
    ReactiveFormsModule
  ]
})
export class AdminCategoriesModule { }
