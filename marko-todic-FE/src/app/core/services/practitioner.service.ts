import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PageDto } from '../models/page.dto';
import { Practitioner } from '../models/practitioner';
import { PractitionerView } from '../models/practitioner-view';

@Injectable({
  providedIn: 'root'
})
export class PractitionerService {

  constructor(private httpClient: HttpClient) { }

  findAll(filter: string, page: number, size: number, sortField: string, sortDir: string) {
    return this.httpClient.get<PageDto<Practitioner>>(`${environment.serverUrl}/api/practitioner?filter=${filter}&pageNumber=${page}&pageSize=${size}&sortField=${sortField}&sortDir=${sortDir}`);
  }

  findAllNoPagination() {
    return this.httpClient.get<PageDto<Practitioner>>(`${environment.serverUrl}/api/practitioner?filter`);
  }

  findAllSimple(): Observable<Practitioner[]>{
    return this.httpClient.get<Practitioner[]>(`${environment.serverUrl}/api/practitioner/findAll`);
  }

  findById(id: number) {
    return this.httpClient.get<Practitioner>(`${environment.serverUrl}/api/practitioner/${id}`);
  }

  findByOrganizationId(id: number): Observable<Practitioner[]> {
    return this.httpClient.get<Practitioner[]>(`${environment.serverUrl}/api/practitioner/findByOrganization/${id}`);
  }

  findByIdView(id: number) {
    return this.httpClient.get<PractitionerView>(`${environment.serverUrl}/api/practitioner/view/${id}`);
  }

  save(practitioner: Practitioner){
    return this.httpClient.post<Practitioner>(`${environment.serverUrl}/api/practitioner`, practitioner);
  }


  update(id: number, practitioner: Practitioner){
    return this.httpClient.put<Practitioner>(`${environment.serverUrl}/api/practitioner/${id}`, practitioner);
  }

  delete(id: number){
    return this.httpClient.put(`${environment.serverUrl}/api/practitioner/delete${id}`, '');
  }

}
