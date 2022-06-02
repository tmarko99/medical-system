import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { RoleGuard } from 'src/app/core/guards/role.guard';
import { PageNotFoundComponent } from 'src/app/pages/page-not-found/page-not-found.component';
import { OrganizationAddComponent } from './pages/organization-add/organization-add.component';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';
import { OrganizationEditComponent } from './pages/organization-edit/organization-edit.component';
import { OrganizationListComponent } from './pages/organization-list/organization-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'organization-list', pathMatch: 'full' },
  { path: 'organization-list', component: OrganizationListComponent },
  { path: 'organization-add', component: OrganizationAddComponent },
  { path: 'organization-edit/:id', component: OrganizationEditComponent },
  { path: 'organization-details/:id', component: OrganizationDetailsComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrganizationRoutingModule { }
