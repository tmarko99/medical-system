import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Gender } from 'src/app/core/models/gender';
import { Organization } from 'src/app/core/models/organization';
import { Qualification } from 'src/app/core/models/qualification';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { PractitionerService } from 'src/app/core/services/practitioner.service';

@Component({
  selector: 'app-practitioner-add',
  templateUrl: './practitioner-add.component.html',
  styleUrls: ['./practitioner-add.component.css']
})
export class PractitionerAddComponent implements OnInit {

  addForm: FormGroup;

  gender = Gender;
  qualifications = Qualification;
  organizations: Organization[] = [];

  constructor(private organizationService: OrganizationService, private router: Router,
    private practitionerService: PractitionerService) { }

  ngOnInit(): void {
    this.organizationService.findAllSimple().subscribe(organizations => {
      this.organizations = organizations;
    });
    this.createForm();
  }

  createForm(){
    this.addForm = new FormGroup({
      identifier: new FormControl('', [Validators.required, Validators.minLength(5)]),
      active: new FormControl(true, [Validators.required]),
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      surname: new FormControl('', [Validators.required, Validators.minLength(3)]),
      gender: new FormControl('', [Validators.required]),
      birthDate: new FormControl('', [Validators.required]),
      address: new FormControl(''),
      phone: new FormControl(''),
      email: new FormControl('', [Validators.email]),
      qualification: new FormControl('', [Validators.required]),
      organization: new FormControl('', [Validators.required])
    })
  }

  hasErrors(controlName: string, error: string){
    const control = this.addForm.get(controlName);

    return control && control.hasError(error) && (control.touched || control.dirty);
  }

  save(){
    const practitioner = this.addForm.getRawValue();
    this.practitionerService.save(practitioner).subscribe(() => {
      this.router.navigate(['/practitioner'], {queryParams: {created: 'true'}});
    });
  }
}
