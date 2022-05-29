import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent implements OnInit {


  @Input() headerText?: string;
  @Input() message?: string;

  returnFunction?: Function;

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  onCancel() {
    this.modal.close('Cancel');
  }

  onConfirm() {
    this.modal.close('Ok');
  }

}
