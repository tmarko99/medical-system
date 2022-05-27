import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrganizationType } from 'src/app/core/models/organizationType';
import { OrganizationService } from 'src/app/core/services/organization.service';

@Component({
  selector: 'app-organization-add',
  templateUrl: './organization-add.component.html',
  styleUrls: ['./organization-add.component.css']
})
export class OrganizationAddComponent implements OnInit {

  addForm: FormGroup;

  organizationTypes = OrganizationType;

  constructor(private organizationService: OrganizationService, private router: Router) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(){
    this.addForm = new FormGroup({
      identifier: new FormControl('', [Validators.required, Validators.minLength(5)]),
      active: new FormControl(true, [Validators.required]),
      type: new FormControl(this.organizationTypes.CLINICAL_RESEARCH, [Validators.required]),
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      address: new FormControl(''),
      phone: new FormControl(''),
      email: new FormControl('', [Validators.email])
    })
  }

  hasErrors(controlName: string, error: string){
    const control = this.addForm.get(controlName);

    return control && control.hasError(error) && (control.touched || control.dirty);
  }

  save(){
    const organization = this.addForm.getRawValue();
    this.organizationService.save(organization).subscribe(() => {
      this.router.navigate(['/organization'], {queryParams: {created: 'true'}});
    });
  }

}
