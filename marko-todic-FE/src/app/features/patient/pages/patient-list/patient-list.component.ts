import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { MaritalStatus } from 'src/app/core/models/maritalStatus';
import { Patient } from 'src/app/core/models/patient';
import { PatientService } from 'src/app/core/services/patient.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header.directive';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  patients: Patient[] = [];
  maritalStatus = MaritalStatus;
  searchValue: string = '';
  maritalStatusValue: string = '';

  currentPage = 1;
  totalItems = 10;
  pageSize = 5;
  sortField:string = 'name';
  sortDir: string = 'asc';
  availablePageSize = [2, 5, 10, 15, 20];

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>

  constructor(private patientService: PatientService, private activatedRoute: ActivatedRoute,
    private toastr: ToastrService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.findAll();
    this.activatedRoute.queryParams.subscribe(params => {
      if(params.created !== undefined && params.created === 'true'){
        this.toastr.success("Patient saved successfully");
      }
      else if(params.updated !== undefined && params.updated === 'true'){
        this.toastr.success("Patient updated successfully");
      }
    })
  }

  findAll(){
    const filter = this.searchValue === '' ? this.maritalStatusValue : this.searchValue;
    this.patientService.findAll(filter, this.currentPage - 1, this.pageSize, this.sortField, this.sortDir).subscribe(patients => {
      this.patients = patients.content;
      this.totalItems = patients.totalElements;
      this.pageSize = patients.pageSize;
    })
  }

  onPageSizeChange(){
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

  onPageChange(){
    this.findAll();
  }

  search(){
    this.findAll();
  }

  onDeleteClick(patient: Patient){
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete patient <strong>${patient.name} ${patient.surname}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting patient';
    modalRef.result.then(result => {
      result === 'Ok' &&  this.deleteSelectedPatient(patient.id);
    })
  }

  deleteSelectedPatient(id: number){
    this.patientService.delete(id).subscribe(() => {
      this.toastr.success("Patient deleted successfully");
      this.findAll();
    })
  }

  getBadgeColorGender(gender){
    switch(gender){
      case 'MALE':
        return 'badge badge-primary';
      case 'FEMALE':
        return 'badge badge-danger';
      case 'OTHER':
        return 'badge badge-dark';
      case 'UNKNOWN':
        return 'badge badge-secondary';
    }
  }

  getBadgeColorMaritalStatus(status){
    switch(status){
      case 'ANNULLED':
        return 'badge badge-primary';
      case 'DIVORCED':
        return 'badge badge-danger';
      case 'MARRIED':
        return 'badge badge-success';
      case 'POLYGAMOUS':
        return 'badge badge-secondary';
      case 'POLYGAMOUS':
        return 'badge badge-warning';
      case 'NEVER_MARRIED':
        return 'badge badge-success';
      case 'WIDOWED':
        return 'badge badge-dark';
    }
  }


}
