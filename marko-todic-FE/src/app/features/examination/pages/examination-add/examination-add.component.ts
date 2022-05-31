import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Organization } from 'src/app/core/models/organization';
import { Patient } from 'src/app/core/models/patient';
import { Practitioner } from 'src/app/core/models/practitioner';
import { Priority } from 'src/app/core/models/priority';
import { ServiceType } from 'src/app/core/models/service-type';
import { Status } from 'src/app/core/models/status';
import { ExaminationService } from 'src/app/core/services/examination.service';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { PatientService } from 'src/app/core/services/patient.service';
import { PractitionerService } from 'src/app/core/services/practitioner.service';
import { ServiceTypeService } from 'src/app/core/services/service-type.service';

@Component({
  selector: 'app-examination-add',
  templateUrl: './examination-add.component.html',
  styleUrls: ['./examination-add.component.css']
})
export class ExaminationAddComponent implements OnInit {

  addForm: FormGroup;
  statuses = Status;
  priority = Priority;
  serviceTypes: ServiceType[] = [];
  organizations: Organization[] = [];
  practitioners: Practitioner[] = [];
  patients: Patient[] = [];

  isSelected: boolean = false;
  selectedOrganization: number;

  constructor(private serviceType: ServiceTypeService, private organizationService: OrganizationService,
    private practitionerService: PractitionerService, private patientSerivce: PatientService,
    private examinationService: ExaminationService, private router: Router) { }

  ngOnInit(): void {
    this.serviceType.findAll().subscribe(serviceTypes => {
      this.serviceTypes = serviceTypes;
    })
    this.organizationService.findAllSimple().subscribe(organizations => {
      this.organizations = organizations;
    })

    this.createForm();
  }

  findByOrganizationId(){
    this.practitionerService.findByOrganizationId(this.selectedOrganization).subscribe(practitioners => {
      this.practitioners = practitioners;
    })

    this.patientSerivce.findByOrganizationId(this.selectedOrganization).subscribe(patients => {
      this.patients = patients;
    })
  }

  createForm(){
    this.addForm = new FormGroup({
      identifier: new FormControl('', [Validators.required, Validators.minLength(5)]),
      status: new FormControl('', [Validators.required]),
      serviceType: new FormControl('', [Validators.required]),
      priority: new FormControl(''),
      startDate: new FormControl(''),
      endDate: new FormControl(''),
      diagnosis: new FormControl(''),
      organization: new FormControl('', [Validators.required]),
      patient: new FormControl('', [Validators.required]),
      practitioners: new FormControl('', [Validators.required]),
    })
  }

  hasErrors(controlName: string, error: string){
    const control = this.addForm.get(controlName);

    return control && control.hasError(error) && (control.touched || control.dirty);
  }


  save(){
    let startDate = this.addForm.get('startDate').value;
    startDate = startDate.replaceAll('T', ' ');

    let endDate = this.addForm.get('endDate').value;
    endDate = endDate.replaceAll('T', ' ');

    const examination = this.addForm.getRawValue();
    examination.startDate = startDate;
    examination.endDate = endDate;

    this.examinationService.save(examination).subscribe(() => {
      return this.router.navigate(['/examination'], {queryParams: { created: 'true' }});
    })
  }


}
