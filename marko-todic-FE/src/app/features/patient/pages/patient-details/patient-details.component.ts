import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientView } from 'src/app/core/models/patient-view';
import { AuthService } from 'src/app/core/services/auth.service';
import { PatientService } from 'src/app/core/services/patient.service';
import { ServiceTypeService } from 'src/app/core/services/service-type.service';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {

  patient: PatientView;
  roleName: string = '';

  constructor(private patientService: PatientService, private activatedRoute: ActivatedRoute,
    private serviceType: ServiceTypeService, private authService: AuthService) { }

  ngOnInit(): void {
    this.roleName = this.authService.getUserRole();

    const patientId = this.activatedRoute.snapshot.params['id'];
    this.patientService.findByIdView(patientId).subscribe(patient => {
      this.patient = patient;
      this.patient.examinations.map((examination) => {
        this.serviceType.findById(Number(examination.serviceType)).subscribe(serviceType => {
          examination.serviceType = serviceType.name;
        })
      })
    })
  }

}
