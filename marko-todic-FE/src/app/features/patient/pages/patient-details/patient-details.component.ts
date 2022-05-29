import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientView } from 'src/app/core/models/patient-view';
import { PatientService } from 'src/app/core/services/patient.service';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {

  patient: PatientView;

  constructor(private patientService: PatientService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const patientId = this.activatedRoute.snapshot.params['id'];
    this.patientService.findByIdView(patientId).subscribe(patient => {
      this.patient = patient;
      console.log(this.patient);

    })
  }

}
