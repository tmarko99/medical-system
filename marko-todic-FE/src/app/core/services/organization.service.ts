import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Organization } from '../models/organization';
import { OrganizationView } from '../models/organization-view';
import { PageDto } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  constructor(private httpClient: HttpClient) { }

  findAll(filter: string, page: number, size: number, sortField: string, sortDir: string){
    return this.httpClient.get<PageDto<Organization>>(`${environment.serverUrl}/api/organization?filter=${filter}&pageNumber=${page}&pageSize=${size}&sortField=${sortField}&sortDir=${sortDir}`);
  }

  findAllNoPagination(){
    return this.httpClient.get<PageDto<Organization>>(`${environment.serverUrl}/api/organization`);
  }


  findAllSimple(): Observable<Organization[]>{
    return this.httpClient.get<Organization[]>(`${environment.serverUrl}/api/organization/findAll`);
  }

  findByIdSimple(id: number): Observable<Organization>{
    return this.httpClient.get<Organization>(`${environment.serverUrl}/api/organization/${id}`);
  }

  findByIdView(id: number): Observable<OrganizationView>{
    return this.httpClient.get<OrganizationView>(`${environment.serverUrl}/api/organization/view/${id}`);
  }

  save(organization: Organization){
    return this.httpClient.post<Organization>(`${environment.serverUrl}/api/organization`, organization);
  }

  update(id: number, organization: Organization){
    return this.httpClient.put<Organization>(`${environment.serverUrl}/api/organization/${id}`, organization);
  }

  delete(id: number){
    return this.httpClient.put(`${environment.serverUrl}/api/organization/delete/${id}`, '');
  }


}
