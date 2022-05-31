import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Gender } from 'src/app/core/models/gender';
import { Practitioner } from 'src/app/core/models/practitioner';
import { Qualification } from 'src/app/core/models/qualification';
import { PractitionerService } from 'src/app/core/services/practitioner.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header.directive';

@Component({
  selector: 'app-practitioner-list',
  templateUrl: './practitioner-list.component.html',
  styleUrls: ['./practitioner-list.component.css']
})
export class PractitionerListComponent implements OnInit {

  practitioners: Practitioner[] = [];

  searchValue: string = '';
  genderValue: string = '';
  unassigned: boolean;
  qualificationValue: string = '';
  gender = Gender;
  qualifications = Qualification;


  currentPage = 1;
  totalItems = 10;
  pageSize = 5;
  sortField:string = 'name';
  sortDir: string = 'asc';
  availablePageSize = [2, 5, 10, 15, 20];

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>

  constructor(private practitionerService: PractitionerService, private toastr: ToastrService,
    private activatedRoute: ActivatedRoute, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      if(params.created !== undefined && params.created === 'true'){
        this.toastr.success("Practitioner saved successfully");
      }
      else if(params.updated !== undefined && params.updated === 'true'){
        this.toastr.success("Practitioner updated successfully");
      }
    })

    this.findAll();

  }

  findAll(){
    const filter = (this.searchValue === '') ? (this.genderValue === '' ? this.qualificationValue : this.genderValue) : this.searchValue;
    const unassignedValue = this.unassigned === true ? 'unassigned' : '';
    const valueForFiltering = unassignedValue !== '' ? unassignedValue : filter;

    this.practitionerService.findAll(valueForFiltering, this.currentPage - 1, this.pageSize, this.sortField, this.sortDir).subscribe(practitioners => {
      this.practitioners = practitioners.content;
      this.totalItems = practitioners.totalElements;
      this.pageSize = practitioners.pageSize;
    })
  }

  onDeleteClick(practitioner: Practitioner){
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete practitioner <strong>${practitioner.name} ${practitioner.surname}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting practitioner';
    modalRef.result.then((result) => {
      result === 'Ok' && this.deleteSelectedPractitioner(practitioner.id);
    })
  }

  deleteSelectedPractitioner(id: number){
    this.practitionerService.delete(id).subscribe(() => {
      this.toastr.success("Practitioner deleted successfully");
      this.findAll();
    })
  }

  onPageChange(){
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

  onPageSizeChange(){
    this.findAll();
  }

  search(){
    this.findAll();
  }

  getBadgeColor(gender){
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

}
