import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrganizationRoutingModule } from './organization-routing.module';
import { OrganizationListComponent } from './pages/organization-list/organization-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { OrganizationAddComponent } from './pages/organization-add/organization-add.component';
import { OrganizationEditComponent } from './pages/organization-edit/organization-edit.component';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';


@NgModule({
  declarations: [OrganizationListComponent, OrganizationAddComponent, OrganizationEditComponent, OrganizationDetailsComponent],
  imports: [
    CommonModule,
    OrganizationRoutingModule,
    SharedModule
  ]
})
export class OrganizationModule { }
