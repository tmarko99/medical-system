import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Organization } from 'src/app/core/models/organization';
import { OrganizationType } from 'src/app/core/models/organizationType';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header.directive';

@Component({
  selector: 'app-organization-list',
  templateUrl: './organization-list.component.html',
  styleUrls: ['./organization-list.component.css']
})
export class OrganizationListComponent implements OnInit {

  organizations: Organization[] = [];

  searchValue: string = '';
  organizationValue: string = '';
  organizationTypes = OrganizationType;

  currentPage = 1;
  totalItems = 10;
  pageSize = 5;
  sortField: string = 'name';
  sortDir: string = 'asc';
  availablePageSize = [2, 5, 10, 15, 20];

  @ViewChildren(SortableHeaderDirective)
  headers: QueryList<SortableHeaderDirective>

  constructor(private organizationService: OrganizationService, private toastr: ToastrService,
    private activatedRoute: ActivatedRoute, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.findAll();
    this.activatedRoute.queryParams.subscribe(params => {
      if(params.created !== undefined && params.created === 'true'){
        this.toastr.success("Organization saved successfully");
      }
      else if(params.updated !== undefined && params.updated === 'true'){
        this.toastr.success("Organization updated successfully");
      }
    })
  }

  findAll(){
    const filter = this.searchValue === '' ? this.organizationValue : this.searchValue;
    this.organizationService.findAll(filter, this.currentPage - 1, this.pageSize, this.sortField, this.sortDir)
    .subscribe(organizations => {
      this.organizations = organizations.content;
      this.totalItems = organizations.totalElements;
      this.pageSize = organizations.pageSize;
    })
  }

  onDeleteClick(organization: Organization) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete organization <strong>${organization.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting organization';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedOrganization(organization.id)
    );
  }

  deleteSelectedOrganization(id: number) {
    this.organizationService.delete(id).subscribe(() => {
      this.toastr.success("Organization deleted successfully");
      this.findAll();
    });
  }

  onPageChange() {
    this.findAll();
  }

  onSort(sortEvent: SortEvent) {
    this.sortField = sortEvent.column;
    this.sortDir = sortEvent.direction;
    this.headers.forEach(header => {
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
    this.findAll();
  }

  getBadgeColor(organizationType){
    switch(organizationType){
      case 'HOSPITAL':
        return 'badge badge-primary';
      case 'INSURANCE_COMPANY':
        return 'badge badge-success';
      case 'EDUCATION_INSTITUTE':
        return 'badge badge-warning';
      case 'CLINICAL_RESEARCH':
        return 'badge badge-info';
      case 'OTHER':
        return 'badge badge-secondary';
    }
  }

}
