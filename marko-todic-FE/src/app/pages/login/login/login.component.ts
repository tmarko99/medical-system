import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private authService: AuthService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
    });
  }

  hasErrors(controlName: string, error: string){
    const control = this.loginForm.get(controlName);

    return control && control.hasError(error) && (control.touched || control.dirty);
  }

  login(){
    const loginDto = this.loginForm.getRawValue();

    this.authService.login(loginDto).subscribe(() => {
      this.router.navigateByUrl('dashboard');
    },
    err => {
          this.toastr.error("Invalid email or password");
      })
  }
}
