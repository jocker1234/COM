import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AdminRoutingModule} from './admin-routing.module';
import {SortableHeaderDirective} from "../../sortable/sortable-header.directive";


@NgModule({
  declarations: [
    SortableHeaderDirective,
  ],
  exports: [
    SortableHeaderDirective,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
  ]
})
export class AdminModule {
}
