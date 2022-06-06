import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Gender } from 'src/app/core/models/gender';
import { MaritalStatus } from 'src/app/core/models/maritalStatus';
import { Organization } from 'src/app/core/models/organization';
import { Practitioner } from 'src/app/core/models/practitioner';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { PatientService } from 'src/app/core/services/patient.service';
import { PractitionerService } from 'src/app/core/services/practitioner.service';

@Component({
  selector: 'app-patient-add',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.css']
})
export class PatientAddComponent implements OnInit {

  addForm: FormGroup;
  organizations: Organization[] = [];
  practitioners: Practitioner[] = [];
  gender = Gender;
  maritalStatus = MaritalStatus;
  isSelected: boolean = false;
  selectedOrganization: number;

  constructor(private patientService: PatientService, private organizationService: OrganizationService,
    private practitionerService: PractitionerService, private router: Router) { }

  ngOnInit(): void {
    this.organizationService.findAllSimple().subscribe(organizations => {
      this.organizations = organizations;
    })

    this.createForm();
  }

  findByOrganizationId(){
    this.practitionerService.findByOrganizationId(this.selectedOrganization).subscribe(practitioners => {
      this.practitioners = practitioners;
    })
  }

  createForm(){
    this.addForm = new FormGroup({
      identifier: new FormControl('', [Validators.required, Validators.minLength(5)]),
      active: new FormControl(true, [Validators.required]),
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      surname: new FormControl('', [Validators.required, Validators.minLength(3)]),
      gender: new FormControl('', [Validators.required]),
      birthDate: new FormControl('', [Validators.required]),
      address: new FormControl(''),
      phone: new FormControl(''),
      email: new FormControl('', [Validators.email]),
      deceased: new FormControl(false),
      maritalStatus: new FormControl(''),
      practitioner: new FormControl('', [Validators.required]),
      organization: new FormControl('', [Validators.required])
    })
  }

  hasErrors(controlName: string, error: string){
    const control = this.addForm.get(controlName);

    return control && control.hasError(error) && (control.touched || control.dirty);
  }

  save(){
    const patient = this.addForm.getRawValue();

    this.patientService.save(patient).subscribe(() => {
      this.router.navigate(['/patient'], {queryParams: { created: 'true' }});
    })
  }

}
