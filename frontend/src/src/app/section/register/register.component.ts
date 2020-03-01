import {Component, OnInit} from '@angular/core';
import {SignUpInfo} from '../auth/signup-info';
import {AuthService} from '../../service/auth.service';
import {Router} from "@angular/router";
import {AbstractControl, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {CustomValidators} from "../../custom-validators";
import {ErrorHandler} from "../error-handler";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  isSignedUp = false;
  isSignUpFailed = false;
  countries: {};
  isChecked: boolean = false;
  regulationsValid = true;
  error: ErrorHandler;

  userRole: string[] = [null, 'Activate Participant', 'Pasive Participant'];
  title: string[] = [null, 'Student', 'PhD Student'];
  yearOfStudy: string[] = [null, '1', '2', '3', '4', '5', '6'];

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
    confirm_password: new FormControl(null, [
      Validators.required,
      RegisterComponent.matchValues('password')
    ]),
    firstName: new FormControl(null, [Validators.required]),
    lastName: new FormControl(null, [Validators.required]),
    //gender: new FormControl(null, [Validators.required]),
    dateOfBirth: new FormControl(null, [Validators.required]),
    country: new FormControl(null, [Validators.required]),
    title: new FormControl(null, [Validators.required]),
    university: new FormControl(null, [Validators.required]),
    faculty: new FormControl(null, [Validators.required]),
    yearOfStudy: new FormControl(null, [Validators.required]),
    phoneNumber: new FormControl(null),
      //CustomValidators.patternValidator(/^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$/,
                                                  //{hasCapitalCase: true})/*[Validators.required]*/),
    //needVisa: new FormControl(null, [Validators.required]),
    passportNumber: new FormControl(null,
      CustomValidators.patternValidator(/^[_A-Za-z]{2}[0-9]{7}$/, {hasCapitalCase: true}))
  });

  public static matchValues(matchTo: string ): (AbstractControl) => ValidationErrors | null {
    return (control: AbstractControl): ValidationErrors | null => {
      return !!control.parent &&
      !!control.parent.value &&
      control.value === control.parent.controls[matchTo].value
        ? null : { isMatching: false };
    };
  }

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.authService.countryList().subscribe(data => this.countries = data);
  }

  /*passportNumberValidation(){
    if(this.registration.get('needVisa').value === 'true') {
      this.registration.addControl('passportNumber', new FormControl(Validators.required));
    }
    console.log(this.registration);
  }*/

  checkBox() {
    this.isChecked = !this.isChecked;
    if (this.isChecked === true) {
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

  checkErrorIsNotUndefined() {
    return this.error !== undefined;
  }

  onSubmit() {
    if (this.isChecked && this.registration.valid) {
      if (this.registration.get('title').value === 'PhD Student') {
        this.registration.get('title').setValue('PhD_Student');
      }
      const date = new Date();
      this.registration.get('dateOfBirth')
        .setValue(formatDate(date, this.registration.get('dateOfBirth').value, 'en-US'));
      this.authService.signUp(this.registration.value).subscribe(
        data => {
          this.isSignedUp = true;
          this.isSignUpFailed = false;
          this.router.navigate(['/login']);
        }, error1 => {
          this.error = new ErrorHandler(error1.error.message);
          scroll(0, 0);
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
