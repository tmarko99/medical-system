import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {

  isLoggedIn: boolean;
  subscription: Subscription;
  role: string;

  constructor(private authService: AuthService, private router: Router) { }


  ngOnInit(): void {
    this.subscription = this.authService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
    this.isLoggedIn = this.authService.isLoggedIn();

    this.subscription = this.authService.role.subscribe((data: string) => this.role = data);
    if(this.isLoggedIn){
      this.role = this.authService.getUserRole();
    }
  }

  onLogout(){
    this.authService.logout();
    this.isLoggedIn = false;
    this.router.navigateByUrl('/').then(() => {
      window.location.reload();
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
