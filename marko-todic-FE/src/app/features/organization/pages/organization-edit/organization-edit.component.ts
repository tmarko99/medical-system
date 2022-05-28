import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Organization } from 'src/app/core/models/organization';
import { OrganizationType } from 'src/app/core/models/organizationType';
import { OrganizationService } from 'src/app/core/services/organization.service';

@Component({
  selector: 'app-organization-edit',
  templateUrl: './organization-edit.component.html',
  styleUrls: ['./organization-edit.component.css']
})
export class OrganizationEditComponent implements OnInit {

  editForm: FormGroup;

  organization: Organization;

  organizationTypes = OrganizationType;

  constructor(private organizationService: OrganizationService, private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    const organizationId = this.activatedRoute.snapshot.params['id'];
    this.organizationService.findByIdSimple(organizationId).subscribe(organization => {
      this.organization = organization;

      this.createForm();
    });
  }

  createForm(){
    this.editForm = new FormGroup({
      id: new FormControl({value: this.organization.id, disabled: true}),
      identifier: new FormControl(this.organization.identifier, [Validators.required, Validators.minLength(5)]),
      active: new FormControl(this.organization.active, [Validators.required]),
      type: new FormControl(this.organization.type, [Validators.required]),
      name: new FormControl(this.organization.name, [Validators.required, Validators.minLength(3)]),
      address: new FormControl(this.organization.name),
      phone: new FormControl(this.organization.phone),
      email: new FormControl(this.organization.email, [Validators.email])
    })
  }

  hasErrors(controlName: string, error: string){
    const control = this.editForm.get(controlName);

    return control && control.hasError(error) && (control.touched || control.dirty);
  }

  update(){
    const organization = this.editForm.getRawValue();

    this.organizationService.update(organization.id, organization).subscribe(() => {
      this.router.navigate(['/organization'], {queryParams: {updated: 'true'}});
    });
  }

}
