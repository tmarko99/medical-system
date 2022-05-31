import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrganizationView } from 'src/app/core/models/organization-view';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { ServiceTypeService } from 'src/app/core/services/service-type.service';

@Component({
  selector: 'app-organization-details',
  templateUrl: './organization-details.component.html',
  styleUrls: ['./organization-details.component.css']
})
export class OrganizationDetailsComponent implements OnInit {

  organization: OrganizationView;

  constructor(private organizationService: OrganizationService, private activatedRoute: ActivatedRoute,
    private serviceType: ServiceTypeService) { }

  ngOnInit(): void {
    const organizationId = this.activatedRoute.snapshot.params['id'];
    this.organizationService.findByIdView(organizationId).subscribe(organization => {
      this.organization = organization;
      this.organization.examinations.map((examination) => {
        this.serviceType.findById(Number(examination.serviceType)).subscribe(serviceType => {
          examination.serviceType = serviceType.name;
        })
      })
    });
  }
}
