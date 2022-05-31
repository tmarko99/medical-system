import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ServiceType } from '../models/service-type';

@Injectable({
  providedIn: 'root'
})
export class ServiceTypeService {

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<ServiceType[]>{
    return this.httpClient.get<ServiceType[]>(`${environment.serverUrl}/api/serviceType`);
  }

  findById(id: number): Observable<ServiceType>{
    return this.httpClient.get<ServiceType>(`${environment.serverUrl}/api/serviceType/${id}`);
  }
}
