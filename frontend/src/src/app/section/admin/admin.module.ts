import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AdminRoutingModule} from './admin-routing.module';
import {SortableTableDirective} from "./sortable-column/sortable-table.directive";
import { SortableColumnComponent } from './sortable-column/sortable-column.component';


@NgModule({
  declarations: [
    SortableTableDirective,
    SortableColumnComponent,
  ],
  exports: [
    SortableTableDirective,
    SortableColumnComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
  ]
})
export class AdminModule {
}
