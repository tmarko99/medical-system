import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Toast, ToastrService } from 'ngx-toastr';
import { Examination } from 'src/app/core/models/examination';
import { Filter } from 'src/app/core/models/filter';
import { Organization } from 'src/app/core/models/organization';
import { Patient } from 'src/app/core/models/patient';
import { Priority } from 'src/app/core/models/priority';
import { ServiceType } from 'src/app/core/models/service-type';
import { Status } from 'src/app/core/models/status';
import { ExaminationService } from 'src/app/core/services/examination.service';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { PatientService } from 'src/app/core/services/patient.service';
import { ServiceTypeService } from 'src/app/core/services/service-type.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header.directive';

@Component({
  selector: 'app-examination-list',
  templateUrl: './examination-list.component.html',
  styleUrls: ['./examination-list.component.css']
})
export class ExaminationListComponent implements OnInit {

  filterForm: FormGroup;

  examinations: Examination[] = [];
  statuses = Status;
  priorities = Priority;

  organizations: Organization[] = [];
  patients: Patient[] = [];
  serviceTypes: ServiceType[] = [];

  filterObject:any = {};

  selectedFile: File;
  isFileSelected = false;
  selectedFileName = '';

  currentPage = 1;
  totalItems = 10;
  pageSize = 5;
  sortField:string = 'id';
  sortDir: string = 'asc';
  availablePageSize = [2, 5, 10, 15, 20];

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>

  constructor(private examinationService: ExaminationService, private modalService: NgbModal,
    private toastr: ToastrService, private organizationService: OrganizationService,
    private patientService: PatientService, private serviceType: ServiceTypeService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.createForm();
    this.organizationService.findAllSimple().subscribe(organizations => {
      this.organizations = organizations;
    });
    this.patientService.findAllSimple().subscribe(patients => {
      this.patients = patients;
    })
    this.serviceType.findAll().subscribe(serviceTypes => {
      this.serviceTypes = serviceTypes;
    })
    this.activatedRoute.queryParams.subscribe(params => {
      if(params.created !== undefined && params.created === 'true'){
        this.toastr.success("Examination saved successfully");
      }
      else if(params.updated !== undefined && params.updated === 'true'){
        this.toastr.success("Examination updated successfully");
      }
    })
  }

  createForm(){
    this.filterForm = new FormGroup({
      status: new FormControl(''),
      priority: new FormControl(''),
      serviceType: new FormControl(''),
      organization: new FormControl(''),
      patient: new FormControl('')
    })
  }

  findAll(){
    this.examinationService.findAll(this.filterObject, this.currentPage - 1, this.pageSize, this.sortField, this.sortDir).subscribe(examinations => {
      this.examinations = examinations.content;
      this.totalItems = examinations.totalElements;
      this.pageSize = examinations.pageSize;
      this.examinations.map((examination) => {
        this.serviceType.findById(Number(examination.serviceType)).subscribe(serviceType => {
          examination.serviceType = serviceType.name;
        })
      })
    })
  }

  selectFile(event) {
    this.selectedFile = event.target.files[0];
    this.selectedFileName = this.selectedFile.name;
    this.isFileSelected = true;
  }

  upload() {
    this.serviceType.upload(this.selectedFile).subscribe(() => {
      this.toastr.success("Uploaded the file successfully");
      this.selectedFile = undefined;
    })


  }

  onPageChange() {
    this.findAll();
  }

  onSort(sortEvent: SortEvent) {
    this.sortField = sortEvent.column;
    this.sortDir = sortEvent.direction;
    this.headers.forEach( header => {
      if (header.sortable !== sortEvent.column) {
        header.direction = '';
      }
    })
    this.findAll();
  }

  onPageSizeChange() {
    this.findAll();
  }

  filter(){
    if(this.filterForm.get('organization').value !== ''){
      this.filterObject.organization = this.filterForm.get('organization').value
    }
    if(this.filterForm.get('patient').value !== ''){
      this.filterObject.patient = this.filterForm.get('patient').value
    }
    if(this.filterForm.get('serviceType').value !== ''){
      this.filterObject.serviceType = this.filterForm.get('serviceType').value
    }
    if(this.filterForm.get('status').value !== ''){
      this.filterObject.status = this.filterForm.get('status').value
    }
    if(this.filterForm.get('priority').value !== ''){
      this.filterObject.priority = this.filterForm.get('priority').value
    }

    this.findAll();
    this.filterObject = {};
  }


  getBadgeColorStatus(status){
    switch(status){
      case 'PLANNED':
        return 'badge badge-info';
      case 'TRIAGED':
        return 'badge badge-secondary';
      case 'IN_PROGRESS':
        return 'badge badge-warning';
      case 'SUSPENDED':
        return 'badge badge-danger';
      case 'FINISHED':
        return 'badge badge-primary';
      case 'CANCELLED':
        return 'badge badge-dark';
    }
  }

  getBadgeColorPriority(status){
    switch(status){
      case 'ASAP':
        return 'badge badge-primary';
      case 'CALLBACK_RESULTS':
        return 'badge badge-primary';
      case 'EMERGENCY':
        return 'badge badge-danger';
      case 'ROUTINE':
        return 'badge badge-info';
      case 'RUSH_REPORT':
        return 'badge badge-warning';
      case 'TIMING_CRITICAL':
        return 'badge badge-danger';
    }
  }


  onDeleteClick(examination: Examination){
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete patient <strong>${examination.identifier}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting patient';
    modalRef.result.then(result => {
      result === 'Ok' &&  this.deleteSelectedExamination(examination.id);
    })
  }

  deleteSelectedExamination(id: number){
    this.examinationService.delete(id).subscribe(() => {
      this.toastr.success("Examination deleted successfully");
    })
  }

}
