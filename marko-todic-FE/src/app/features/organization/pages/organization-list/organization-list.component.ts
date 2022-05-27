import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Organization } from 'src/app/core/models/organization';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-organization-list',
  templateUrl: './organization-list.component.html',
  styleUrls: ['./organization-list.component.css']
})
export class OrganizationListComponent implements OnInit {

  organizations: Organization[] = [];

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
    this.organizationService.findAll().subscribe(organizations => {
      this.organizations = organizations['content'];
    })
  }

  onDeleteClick(organization: Organization) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete organization <strong>${organization.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting organization';
    modalRef.result.then(
      // NAPOMENA: Ovde ce samo ako je zadovoljen prvi uslov izvrsiti ovo drugo.
      (result) => result === 'Ok' && this.deleteSelectedOrganization(organization.id)
    );
  }

  deleteSelectedOrganization(id: number) {
    this.organizationService.delete(id).subscribe(() => {
      this.toastr.success("Organization deleted successfully");
      this.findAll();
    });
  }

}
