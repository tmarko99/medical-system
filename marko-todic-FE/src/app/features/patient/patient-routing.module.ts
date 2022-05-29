import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PatientAddComponent } from './pages/patient-add/patient-add.component';
import { PatientDetailsComponent } from './pages/patient-details/patient-details.component';
import { PatientEditComponent } from './pages/patient-edit/patient-edit.component';
import { PatientListComponent } from './pages/patient-list/patient-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'patient-list', pathMatch: 'full' },
  { path: 'patient-list', component: PatientListComponent },
  { path: 'patient-add', component: PatientAddComponent },
  { path: 'patient-edit/:id', component: PatientEditComponent },
  { path: 'patient-details/:id', component: PatientDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientRoutingModule { }
