import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Practitioner } from 'src/app/core/models/practitioner';
import { PractitionerService } from 'src/app/core/services/practitioner.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-practitioner-list',
  templateUrl: './practitioner-list.component.html',
  styleUrls: ['./practitioner-list.component.css']
})
export class PractitionerListComponent implements OnInit {

  practitioners: Practitioner[] = [];

  constructor(private practitionerService: PractitionerService, private toastr: ToastrService,
    private activatedRoute: ActivatedRoute, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      if(params.created !== undefined && params.created === 'true'){
        this.toastr.success("Organization saved successfully");
      }
      else if(params.updated !== undefined && params.updated === 'true'){
        this.toastr.success("Organization updated successfully");
      }
    })

    this.findAll();

  }

  findAll(){
    this.practitionerService.findAll().subscribe(practitioners => {
      this.practitioners = practitioners.content;
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
