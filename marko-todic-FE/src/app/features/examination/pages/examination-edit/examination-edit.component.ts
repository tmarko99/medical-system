import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Examination } from 'src/app/core/models/examination';
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
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-examination-edit',
  templateUrl: './examination-edit.component.html',
  styleUrls: ['./examination-edit.component.css']
})
export class ExaminationEditComponent implements OnInit {

  editForm: FormGroup;

  examination: Examination;

  statuses = Status;
  priority = Priority;
  serviceTypes: ServiceType[] = [];
  organizations: Organization[] = [];
  practitioners: Practitioner[] = [];
  patients: Patient[] = [];

  isSelected: boolean = true;
  selectedOrganization: number;

  constructor(private serviceType: ServiceTypeService, private organizationService: OrganizationService,
    private practitionerService: PractitionerService, private patientSerivce: PatientService,
    private examinationService: ExaminationService, private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.serviceType.findAll().subscribe(serviceTypes => {
      this.serviceTypes = serviceTypes;
    })
    this.organizationService.findAllSimple().subscribe(organizations => {
      this.organizations = organizations;
    })

    const examinationId = this.activatedRoute.snapshot.params['id'];
    this.examinationService.findById(examinationId).subscribe(examination => {
      this.examination = examination;
      this.selectedOrganization = examination.organization;
      this.createForm();
      this.findByOrganizationId();
    })
  }

  createForm(){
    this.editForm = new FormGroup({
      id: new FormControl({value: this.examination.id, disabled: true}),
      identifier: new FormControl(this.examination.identifier, [Validators.required, Validators.minLength(5)]),
      status: new FormControl(this.examination.status, [Validators.required]),
      serviceType: new FormControl(this.examination.serviceType, [Validators.required]),
      priority: new FormControl(this.examination.priority),
      startDate: new FormControl(formatDate(this.examination.startDate, 'yyyy-MM-ddTHH:mm', 'en')),
      endDate: new FormControl(formatDate(this.examination.endDate, 'yyyy-MM-ddTHH:mm', 'en')),
      diagnosis: new FormControl(this.examination.diagnosis),
      organization: new FormControl(this.examination.organization, [Validators.required]),
      patient: new FormControl(this.examination.patient, [Validators.required]),
      practitioners: new FormControl(this.examination.practitioners, [Validators.required]),
    })
  }

  findByOrganizationId(){
    this.practitionerService.findByOrganizationId(this.selectedOrganization).subscribe(practitioners => {
      this.practitioners = practitioners;
    })

    this.patientSerivce.findByOrganizationId(this.selectedOrganization).subscribe(patients => {
      this.patients = patients;
    })
  }

  hasErrors(controlName: string, error: string){
    const control = this.editForm.get(controlName);

    return control && control.hasError(error) && (control.touched || control.dirty);
  }

  update(){
    let startDate = this.editForm.get('startDate').value;
    startDate = startDate.replaceAll('T', ' ');

    let endDate = this.editForm.get('endDate').value;
    endDate = endDate.replaceAll('T', ' ');

    const examination = this.editForm.getRawValue();
    examination.startDate = startDate;
    examination.endDate = endDate;

    this.examinationService.update(examination.id, examination).subscribe(() => {
      return this.router.navigate(['/examination'], {queryParams: { updated: 'true' }});
    })
  }


}
