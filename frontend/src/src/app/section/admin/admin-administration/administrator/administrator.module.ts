import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdministratorRoutingModule } from './administrator-routing.module';
import {AdministratorCreateComponent} from "./administrator-create/administrator-create.component";
import {AdministratorDetailComponent} from "./administrator-detail/administrator-detail.component";
import {AdministratorListComponent} from "./administrator-list/administrator-list.component";
import {FieldErrorDisplayModule} from "../../../field-error-display/field-error-display.module";
import {ReactiveFormsModule} from "@angular/forms";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";


@NgModule({
  declarations: [
    AdministratorCreateComponent,
    AdministratorDetailComponent,
    AdministratorListComponent,
  ],
  imports: [
    CommonModule,
    AdministratorRoutingModule,
    FieldErrorDisplayModule,
    ReactiveFormsModule,
    FontAwesomeModule,
  ]
})
export class AdministratorModule { }
