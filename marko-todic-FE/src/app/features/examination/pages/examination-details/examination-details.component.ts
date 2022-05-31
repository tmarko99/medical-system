import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExaminationView } from 'src/app/core/models/examination-view';
import { ExaminationService } from 'src/app/core/services/examination.service';

@Component({
  selector: 'app-examination-details',
  templateUrl: './examination-details.component.html',
  styleUrls: ['./examination-details.component.css']
})
export class ExaminationDetailsComponent implements OnInit {

  examination: ExaminationView;

  constructor(private examinationService: ExaminationService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const examinationId = this.activatedRoute.snapshot.params['id'];
    this.examinationService.findByIdView(examinationId).subscribe(examination => {
      this.examination = examination;
    })

  }

}
