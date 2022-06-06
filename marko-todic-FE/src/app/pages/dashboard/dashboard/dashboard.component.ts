import { Component, OnInit } from '@angular/core';
import { OrganizationService } from 'src/app/core/services/organization.service';
import { Chart, registerables  } from 'chart.js';
import { PractitionerService } from 'src/app/core/services/practitioner.service';
import { PatientService } from 'src/app/core/services/patient.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  results: any;

  organizationNames: any;
  numberOfPractitioners: any;
  numberOfExaminations: any;
  chart: any = [];
  qualificationNames: any;
  practitioners: any;
  numberOfTitles: any;

  patientsAge: any;
  patients: any;


  constructor(private organizationService: OrganizationService, private practitionerService: PractitionerService,
    private patientService: PatientService) {
    Chart.register(...registerables);
   }

  ngOnInit(): void {
    this.organizationService.findAllNoPagination().subscribe(organizations => {
      this.results = organizations.content;

      this.organizationNames = this.results.map((organization) => organization.name);
      this.numberOfPractitioners = this.results.map((organization) => organization.numberOfPractitioners);

      this.numberOfExaminations = this.results.map((organization) => organization.numberOfExaminations);

      this.chart = new Chart('canvas', {
        type: 'bar',
        data: {
            labels: this.organizationNames,
            datasets: [{
                label: 'Number of practitioners by each organization',
                data: this.numberOfPractitioners,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    this.chart = new Chart('canvas2', {
      type: 'bar',
      data: {
          labels: this.organizationNames,
          datasets: [{
              label: 'Number of examinations by each organization',
              data: this.numberOfExaminations,
              backgroundColor: [
                  'rgba(255, 99, 132, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
                  'rgba(255, 206, 86, 0.2)',
                  'rgba(75, 192, 192, 0.2)',
                  'rgba(153, 102, 255, 0.2)',
                  'rgba(255, 159, 64, 0.2)'
              ],
              borderColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)',
                  'rgba(75, 192, 192, 1)',
                  'rgba(153, 102, 255, 1)',
                  'rgba(255, 159, 64, 1)'
              ],
              borderWidth: 1
          }]
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
  });

    })

    this.practitionerService.findAllNoPagination().subscribe(practitioners => {
      this.practitioners = practitioners.content;

      this.qualificationNames = [...new Set(this.practitioners.map((practitioner) => practitioner.qualification))];

      let practitionerQualifications = [
        {name: 'DOCTOR_OF_MEDICINE', value: 0},
        {name: 'MEDICAL_ASSISTANT', value: 0},
        {name: 'NURSE_PRACTITIONER', value: 0},
        {name: 'DOCTOR_OF_PHARMACY', value: 0},
        {name: 'CERTIFIED_NURSE_MIDWIFE', value: 0},
        {name: 'EMERGENCY_MEDICAL_TECHNICIAN', value: 0}
      ]

      this.practitioners.map((practitioner) => {
        let qualification = practitioner.qualification;
        practitionerQualifications.forEach(title => {
          if(title.name === qualification){
            title.value++;
          }
        })
      });

      const qualificationNumber = practitionerQualifications.map(title => title.value).filter(value => value > 0);

      this.chart = new Chart('canvas3', {
        type: 'pie',
        data: {
            labels: this.qualificationNames,
            datasets: [{
                label: 'Number of titles',
                data: qualificationNumber,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ]
            }]
        }
    });


    })

    this.patientService.findAllNoPagination().subscribe(patients => {
      this.patients = patients.content;

      let agesByGender = [
        {name: 'MALE', age: 0},
        {name: 'FEMALE', age: 0},

      ]
      let numberOfMans = 0;
      let numberOfWomans = 0;

      this.patientsAge = this.patients.map(patient => {
        let gender = patient.gender;
        if(gender === 'MALE'){
          numberOfMans++;
        }
        else if(gender === 'FEMALE'){
          numberOfWomans++;
        }

        let age = Math.floor((new Date().getTime() - new Date(patient.birthDate).getTime()) / 3.154e+10);
        agesByGender.forEach(gendr => {
          if(gendr.name === gender){
            gendr.age += age;
          }
        })

      });

      const averageAgeMan = agesByGender.filter(gender => gender.name === 'MALE').map(gender => gender.age).reduce((prev, curr) => {
        return prev + curr
      }, 0) / numberOfMans;

      const averageAgeWomans = agesByGender.filter(gender => gender.name === 'FEMALE').map(gender => gender.age).reduce((prev, curr) => {
        return prev + curr
      }, 0) / numberOfWomans;

      this.chart = new Chart('canvas4', {
        type: 'bar',
        data: {
            labels: ['MALE', 'FEMALE'],
            datasets: [{
                label: 'Patients average age by gender',
                data: [averageAgeMan, averageAgeWomans],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    })
    })
  }
}
