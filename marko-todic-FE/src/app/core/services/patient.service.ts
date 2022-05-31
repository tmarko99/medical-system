import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PageDto } from '../models/page.dto';
import { Patient } from '../models/patient';
import { PatientView } from '../models/patient-view';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private httpClient: HttpClient) { }


  findAll(filter: string, page: number, size: number, sortField: string, sortDir: string){
    return this.httpClient.get<PageDto<Patient>>(`${environment.serverUrl}/api/patient?filter=${filter}&pageNumber=${page}&pageSize=${size}&sortField=${sortField}&sortDir=${sortDir}`);
  }

  findAllSimple(): Observable<Patient[]>{
    return this.httpClient.get<Patient[]>(`${environment.serverUrl}/api/patient/findAll`);
  }

  findById(id: number){
    return this.httpClient.get<Patient>(`${environment.serverUrl}/api/patient/${id}`);
  }

  findByIdView(id: number){
    return this.httpClient.get<PatientView>(`${environment.serverUrl}/api/patient/view/${id}`);
  }

  findByOrganizationId(id: number): Observable<Patient[]> {
    return this.httpClient.get<Patient[]>(`${environment.serverUrl}/api/patient/findByOrganization/${id}`);
  }


  save(patient: Patient){
    return this.httpClient.post<Patient>(`${environment.serverUrl}/api/patient`, patient);
  }

  update(id: number, patient: Patient){
    return this.httpClient.put<Patient>(`${environment.serverUrl}/api/patient/${id}`, patient);
  }

  delete(id: number){
    return this.httpClient.put(`${environment.serverUrl}/api/patient/delete/${id}`, '');
  }
}
