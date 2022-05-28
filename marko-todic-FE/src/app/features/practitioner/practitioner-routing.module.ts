import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PractitionerAddComponent } from './pages/practitioner-add/practitioner-add.component';
import { PractitionerDetailsComponent } from './pages/practitioner-details/practitioner-details.component';
import { PractitionerEditComponent } from './pages/practitioner-edit/practitioner-edit.component';
import { PractitionerListComponent } from './pages/practitioner-list/practitioner-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'practitioner-list', pathMatch: 'full' },
  { path: 'practitioner-list', component: PractitionerListComponent },
  { path: 'practitioner-add', component: PractitionerAddComponent },
  { path: 'practitioner-edit/:id', component: PractitionerEditComponent },
  { path: 'practitioner-details/:id', component: PractitionerDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PractitionerRoutingModule { }
