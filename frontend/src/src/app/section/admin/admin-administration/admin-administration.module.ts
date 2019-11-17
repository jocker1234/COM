import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminAdministrationRoutingModule } from './admin-administration-routing.module';
import { DirectoryComponent } from './directory/directory.component';
import { AdministratorComponent } from './administrator/administrator.component';


@NgModule({
  declarations: [DirectoryComponent, AdministratorComponent],
  imports: [
    CommonModule,
    AdminAdministrationRoutingModule
  ]
})
export class AdminAdministrationModule { }
