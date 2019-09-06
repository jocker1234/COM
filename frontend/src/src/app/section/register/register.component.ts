import {Component, OnInit} from '@angular/core';
import {SignUpInfo} from '../auth/signup-info';
import {AuthService} from '../auth/auth.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  countries: {};

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.authService.countryList().subscribe(data => this.countries = data);
  }

  onSubmit() {
    this.signupInfo = new SignUpInfo(
      this.form.email,
      this.form.password,
      this.form.firstName,
      this.form.lastName,
      this.form.gender,
      this.form.dateOfBirth,
      this.form.country,
      this.form.title,
      this.form.university,
      this.form.faculty,
      this.form.yearOfStudy,
      this.form.phoneNumber,
      this.form.needVisa,
      this.form.passportNumber,
      [this.form.authorities]
    );

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.router.navigate(['/login']);
      },
      error => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
