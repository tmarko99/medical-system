import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ExaminationAddComponent } from './pages/examination-add/examination-add.component';
import { ExaminationEditComponent } from './pages/examination-edit/examination-edit.component';
import { ExaminationListComponent } from './pages/examination-list/examination-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'examination-list', pathMatch: 'full' },
  { path: 'examination-list', component: ExaminationListComponent },
  { path: 'examination-add', component: ExaminationAddComponent },
  { path: 'examination-edit/:id', component: ExaminationEditComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExaminationRoutingModule { }
