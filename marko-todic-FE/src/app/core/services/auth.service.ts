import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { LoginResponse } from '../models/login-response';
import { LoginDto } from '../models/login.dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() role: EventEmitter<string> = new EventEmitter();

  constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) { }


  login(loginDto: LoginDto): Observable<boolean>{
    return this.httpClient.post<LoginResponse>(`${environment.serverUrl}/api/auth/login`, loginDto)
                          .pipe(map(user => {
                            if(user.accessToken){
                              this.localStorage.store('accessToken', user.accessToken);
                              this.localStorage.store('userRole', user.authorities);

                              this.loggedIn.emit(true);

                              const roles = user.authorities.map(obj => {
                                return obj.authority;
                              });

                              this.role.emit(roles[0]);

                              return true;
                            }
                          }));
  }


  isLoggedIn(): boolean{
    return this.getJwtToken() != null;
  }

  getJwtToken(){
    return this.localStorage.retrieve('accessToken');
  }

  getUserRole(){
    const roles = this.localStorage.retrieve('userRole');
    return roles.map(obj => {
      return obj.authority;
    });
  }

  logout(){
    this.localStorage.clear('accessToken');
    this.localStorage.clear('userRole');
  }
}
