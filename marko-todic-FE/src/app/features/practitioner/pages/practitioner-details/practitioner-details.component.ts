import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PractitionerView } from 'src/app/core/models/practitioner-view';
import { PractitionerService } from 'src/app/core/services/practitioner.service';

@Component({
  selector: 'app-practitioner-details',
  templateUrl: './practitioner-details.component.html',
  styleUrls: ['./practitioner-details.component.css']
})
export class PractitionerDetailsComponent implements OnInit {

  practitonerView: PractitionerView;

  constructor(private practitonerService: PractitionerService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const practitionerId = this.activatedRoute.snapshot.params['id'];
    this.practitonerService.findByIdView(practitionerId).subscribe(practitioner => {
      this.practitonerView = practitioner;

      console.log(this.practitonerView);

    })
  }

}
