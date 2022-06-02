import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Examination } from '../models/examination';
import { ExaminationView } from '../models/examination-view';
import { Filter } from '../models/filter';
import { PageDto } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class ExaminationService {

  constructor(private httpClient: HttpClient) { }

  findAll(filter: Filter, page: number, size: number, sortField: string, sortDir: string){
    return this.httpClient.post<PageDto<Examination>>(`${environment.serverUrl}/api/examination/filter?pageNumber=${page}&pageSize=${size}&sortField=${sortField}&sortDir=${sortDir}`, filter);
  }

  findAllNoPagination(){
    return this.httpClient.post<PageDto<Examination>>(`${environment.serverUrl}/api/examination/filter`, {});
  }


  findById(id: number): Observable<Examination>{
    return this.httpClient.get<Examination>(`${environment.serverUrl}/api/examination/${id}`);
  }

  findByIdView(id: number): Observable<ExaminationView>{
    return this.httpClient.get<ExaminationView>(`${environment.serverUrl}/api/examination/view/${id}`);
  }

  save(examination: Examination): Observable<Examination>{
    return this.httpClient.post<Examination>(`${environment.serverUrl}/api/examination`, examination);
  }

  update(id: number, examination: Examination): Observable<Examination>{
    return this.httpClient.put<Examination>(`${environment.serverUrl}/api/examination/${id}`, examination);
  }

  delete(id: number){
    return this.httpClient.put(`${environment.serverUrl}/api/examination/delete/${id}`, '');
  }

}
