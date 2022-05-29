import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: 'organization',
    loadChildren: () => import('./features/organization/organization.module').then((m) => m.OrganizationModule)
  },
  { path: 'practitioner',
    loadChildren: () => import('./features/practitioner/practitioner.module').then((m) => m.PractitionerModule)
  },
  { path: 'patient',
    loadChildren: () => import('./features/patient/patient.module').then((m) => m.PatientModule)
  },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
