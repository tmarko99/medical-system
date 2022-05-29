import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Gender } from 'src/app/core/models/gender';
import { MaritalStatus } from 'src/app/core/models/maritalStatus';
import { Organization } from 'src/app/core/models/organization';
import { Patient } from 'src/app/core/models/patient';
import { Practitioner } from 'src/app/core/models/practitioner';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { PatientService } from 'src/app/core/services/patient.service';
import { PractitionerService } from 'src/app/core/services/practitioner.service';

@Component({
  selector: 'app-patient-edit',
  templateUrl: './patient-edit.component.html',
  styleUrls: ['./patient-edit.component.css']
})
export class PatientEditComponent implements OnInit {

  editForm: FormGroup;

  patient: Patient;

  organizations: Organization[] = [];
  practitioners: Practitioner[] = [];
  gender = Gender;
  maritalStatus = MaritalStatus;
  isSelected: boolean = true;
  selectedOrganization: number;


  constructor(private patientService: PatientService, private activatedRoute: ActivatedRoute,
    private organizationService: OrganizationService, private practitionerService: PractitionerService,
    private router: Router) { }

  ngOnInit(): void {
    this.organizationService.findAllSimple().subscribe(organizations => {
      this.organizations = organizations;
    })

    const patientId = this.activatedRoute.snapshot.params['id'];
    this.patientService.findById(patientId).subscribe(patient => {
      this.patient = patient;
      this.selectedOrganization = patient.organization;
      this.createForm();
      this.findByOrganizationId();
    })
  }

  findByOrganizationId(){
    this.practitionerService.findByOrganizationId(this.selectedOrganization).subscribe(practitioners => {
      this.practitioners = practitioners;
    })
  }

  createForm(){
    this.editForm = new FormGroup({
      id: new FormControl({value: this.patient.id, disabled: true}),
      identifier: new FormControl(this.patient.identifier, [Validators.required, Validators.minLength(5)]),
      active: new FormControl(this.patient.active, [Validators.required]),
      name: new FormControl(this.patient.name, [Validators.required, Validators.minLength(3)]),
      surname: new FormControl(this.patient.surname, [Validators.required, Validators.minLength(3)]),
      gender: new FormControl(this.patient.gender, [Validators.required]),
      birthDate: new FormControl(this.patient.birthDate, [Validators.required]),
      address: new FormControl(this.patient.address),
      phone: new FormControl(this.patient.phone),
      email: new FormControl(this.patient.email, [Validators.email]),
      deceased: new FormControl(this.patient.deceased),
      maritalStatus: new FormControl(this.patient.maritalStatus),
      practitioner: new FormControl(this.patient.practitioner, [Validators.required]),
      organization: new FormControl(this.patient.organization, [Validators.required])
    })
  }

  hasErrors(controlName: string, error: string){
    const control = this.editForm.get(controlName);

    return control && control.hasError(error) && (control.touched || control.dirty);
  }

  update(){
    const patient = this.editForm.getRawValue();
    this.patientService.update(patient.id, patient).subscribe(() => {
      this.router.navigate(['/patient'], {queryParams: { updated: 'true' }});
    })
  }

}
