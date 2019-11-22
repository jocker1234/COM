import { Component, OnInit } from '@angular/core';
import {ErrorHandler} from "../../../../error-handler";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../../../../../custom-validators";
import {AuthService} from "../../../../../service/auth.service";
import {UserService} from "../../../../../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-administrator-create',
  templateUrl: './administrator-create.component.html',
  styleUrls: ['./administrator-create.component.scss']
})
export class AdministratorCreateComponent implements OnInit {

  error: ErrorHandler;
  countries: {};

  createAdmin = new FormGroup({
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
    firstName: new FormControl(null),
    lastName: new FormControl(null),
    //gender: new FormControl(null, [Validators.required]),
    dateOfBirth: new FormControl(null),
    country: new FormControl(null),
    phoneNumber: new FormControl(null),
  });

  constructor(private authService: AuthService, private userService: UserService,
              private router: Router) { }

  ngOnInit() {
    this.authService.countryList().subscribe(data => this.countries = data);
  }

  isFieldValid(field: string) {
    return !this.createAdmin.get(field).valid && this.createAdmin.get(field).touched;
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
    this.userService.createAdmin(this.createAdmin.value).subscribe(
      data => {
        this.router.navigate(['/admin/administration/admins']);
      }, error1 => {
        this.error = new ErrorHandler(error1.error.message);
        scroll(0,0)
      }
    );
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
