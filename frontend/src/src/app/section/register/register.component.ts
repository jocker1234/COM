import {Component, OnInit} from '@angular/core';
import {SignUpInfo} from '../auth/signup-info';
import {AuthService} from '../auth/auth.service';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../../custom-validators";

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
  isChecked: boolean = false;
  regulationsValid = true;

  userRole: string[] = [null,'Activate Participant', 'Pasive Participant'];

  registration = new FormGroup({
    authorities: new FormControl(null, [Validators.required]),
    email: new FormControl(null, Validators.compose([
      Validators.email,
      Validators.required
    ])),
    password: new FormControl(null, Validators.compose([
      // 1. Password Field is Required
      Validators.required,
      // 2. check whether the entered password has a number
      CustomValidators.patternValidator(/\d/, {hasNumber: true}),
      // 3. check whether the entered password has upper case letter
      CustomValidators.patternValidator(/[A-Z]/, {hasCapitalCase: true}),
      // 4. check whether the entered password has a lower-case letter
      CustomValidators.patternValidator(/[a-z]/, {hasSmallCase: true}),
      // 6. Has a minimum length of 8 characters
      Validators.minLength(8)
    ])),
    firstName: new FormControl(null, [Validators.required]),
    lastName: new FormControl(null, [Validators.required]),
    gender: new FormControl(null, [Validators.required]),
    dateOfBirth: new FormControl(null, [Validators.required]),
    country: new FormControl(null, [Validators.required]),
    title: new FormControl(null, [Validators.required]),
    university: new FormControl(null, [Validators.required]),
    faculty: new FormControl(null, [Validators.required]),
    yearOfStudy: new FormControl(null, [Validators.required]),
    phoneNumber: new FormControl(null, [Validators.required]),
    needVisa: new FormControl(null, [Validators.required]),
    passportNumber: new FormControl(null)
  });

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.authService.countryList().subscribe(data => this.countries = data);
  }

  checkBox() {
    this.isChecked = !this.isChecked;
    if (this.isChecked == true) {
      this.regulationsValid = true;
    }
  }

  isFieldValid(field: string) {
    return !this.registration.get(field).valid && this.registration.get(field).touched;
  }

  displayFieldCss(field: string) {
    return {
      'has-error': this.isFieldValid(field),
      'has-feedback': this.isFieldValid(field)
    };
  }

  onSubmit() {
    if (this.isChecked && this.registration.valid) {
      this.authService.signUp(this.registration.value).subscribe(
        data => {
          this.isSignedUp = true;
          this.isSignUpFailed = false;
          this.router.navigate(['/login']);
        }
      );
    } else {
      if (!this.isChecked) {
        this.regulationsValid = this.isChecked;
      }
      this.validateAllFormFields(this.registration);
    }
  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({onlySelf: true});
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }
}
