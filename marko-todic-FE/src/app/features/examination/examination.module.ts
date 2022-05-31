import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExaminationRoutingModule } from './examination-routing.module';
import { ExaminationListComponent } from './pages/examination-list/examination-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ExaminationAddComponent } from './pages/examination-add/examination-add.component';
import { ExaminationEditComponent } from './pages/examination-edit/examination-edit.component';
import { ExaminationDetailsComponent } from './pages/examination-details/examination-details.component';


@NgModule({
  declarations: [ExaminationListComponent, ExaminationAddComponent, ExaminationEditComponent, ExaminationDetailsComponent],
  imports: [
    CommonModule,
    ExaminationRoutingModule,
    SharedModule
  ]
})
export class ExaminationModule { }
