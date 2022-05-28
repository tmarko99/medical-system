import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { PageDto } from '../models/page.dto';
import { Practitioner } from '../models/practitioner';
import { PractitionerView } from '../models/practitioner-view';

@Injectable({
  providedIn: 'root'
})
export class PractitionerService {

  constructor(private httpClient: HttpClient) { }

  findAll() {
    return this.httpClient.get<PageDto<Practitioner>>(`${environment.serverUrl}/api/practitioner`);
  }

  findById(id: number) {
    return this.httpClient.get<Practitioner>(`${environment.serverUrl}/api/practitioner/${id}`);
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
