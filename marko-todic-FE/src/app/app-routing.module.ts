import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';
import { RoleGuard } from './core/guards/role.guard';
import { DashboardComponent } from './pages/dashboard/dashboard/dashboard.component';
import { LoginComponent } from './pages/login/login/login.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path:'login', component: LoginComponent },
  { path:'dashboard', component: DashboardComponent,  canActivate: [AuthGuard] },
  { path: 'organization', canActivate: [AuthGuard, RoleGuard],
      data: {
        roles: [
          { authority: 'ROLE_ADMIN'}
        ]
      },
    loadChildren: () => import('./features/organization/organization.module').then((m) => m.OrganizationModule)
  },
  { path: 'practitioner', canActivate: [AuthGuard, RoleGuard],
      data: {
        roles: [
          { authority: 'ROLE_ADMIN'},
          { authority: 'ROLE_PRACTITIONER'}
        ]
      },
    loadChildren: () => import('./features/practitioner/practitioner.module').then((m) => m.PractitionerModule)
  },
  { path: 'patient', canActivate: [AuthGuard, RoleGuard],
      data: {
        roles: [
          { authority: 'ROLE_ADMIN'},
          { authority: 'ROLE_PATIENT'},
          { authority: 'ROLE_PRACTITIONER'}
        ]
      },
    loadChildren: () => import('./features/patient/patient.module').then((m) => m.PatientModule)
  },
  { path: 'examination', canActivate: [AuthGuard, RoleGuard],
      data: {
        roles: [
          { authority: 'ROLE_ADMIN'},
          { authority: 'ROLE_PRACTITIONER'}
        ]
      },
    loadChildren: () => import('./features/examination/examination.module').then((m) => m.ExaminationModule)
  },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
