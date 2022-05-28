import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Gender } from 'src/app/core/models/gender';
import { Organization } from 'src/app/core/models/organization';
import { Practitioner } from 'src/app/core/models/practitioner';
import { Qualification } from 'src/app/core/models/qualification';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { PractitionerService } from 'src/app/core/services/practitioner.service';

@Component({
  selector: 'app-practitioner-edit',
  templateUrl: './practitioner-edit.component.html',
  styleUrls: ['./practitioner-edit.component.css']
})
export class PractitionerEditComponent implements OnInit {

  editForm: FormGroup;
  gender = Gender;
  qualifications = Qualification;
  practitioner: Practitioner;
  organizations: Organization[] = [];

  constructor(private practitionerService: PractitionerService, private activatedRoute: ActivatedRoute,
    private organizationService: OrganizationService, private router: Router) { }

  ngOnInit(): void {
    this.organizationService.findAllSimple().subscribe(organizations => {
      this.organizations = organizations;
    });

    const practitionerId = this.activatedRoute.snapshot.params['id'];
    this.practitionerService.findById(practitionerId).subscribe(practitioner => {
      this.practitioner = practitioner;
      this.createForm();
    });
  }

  createForm(){
    this.editForm = new FormGroup({
      id: new FormControl({value: this.practitioner.id, disabled: true}),
      identifier: new FormControl(this.practitioner.identifier, [Validators.required, Validators.minLength(5)]),
      active: new FormControl(this.practitioner.active, [Validators.required]),
      name: new FormControl(this.practitioner.name, [Validators.required, Validators.minLength(3)]),
      surname: new FormControl(this.practitioner.surname, [Validators.required, Validators.minLength(3)]),
      gender: new FormControl(this.practitioner.gender, [Validators.required]),
      birthDate: new FormControl(this.practitioner.birthDate, [Validators.required]),
      address: new FormControl(this.practitioner.address),
      phone: new FormControl(this.practitioner.phone),
      email: new FormControl(this.practitioner.email, [Validators.email]),
      qualification: new FormControl(this.practitioner.qualification, [Validators.required]),
      organization: new FormControl(this.practitioner.organization, [Validators.required])
    })
  };

  hasErrors(controlName: string, error: string){
    const control = this.editForm.get(controlName);

    return control && control.hasError(error) && (control.touched || control.dirty);
  }

  update(){
    const practitioner = this.editForm.getRawValue();
    this.practitionerService.update(practitioner.id, practitioner).subscribe(() => {
      this.router.navigate(['/practitioner'], {queryParams: {updated: 'true'}});
    })
  }

}
