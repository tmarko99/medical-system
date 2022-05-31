import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PractitionerView } from 'src/app/core/models/practitioner-view';
import { PractitionerService } from 'src/app/core/services/practitioner.service';
import { ServiceTypeService } from 'src/app/core/services/service-type.service';

@Component({
  selector: 'app-practitioner-details',
  templateUrl: './practitioner-details.component.html',
  styleUrls: ['./practitioner-details.component.css']
})
export class PractitionerDetailsComponent implements OnInit {

  practitoner: PractitionerView;

  constructor(private practitonerService: PractitionerService, private activatedRoute: ActivatedRoute,
    private serviceType: ServiceTypeService) { }

  ngOnInit(): void {
    const practitionerId = this.activatedRoute.snapshot.params['id'];
    this.practitonerService.findByIdView(practitionerId).subscribe(practitioner => {
      this.practitoner = practitioner;
      this.practitoner.examinations.map((examination) => {
        this.serviceType.findById(Number(examination.serviceType)).subscribe(serviceType => {
          examination.serviceType = serviceType.name;
        })
      })
    })
  }

}
