import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../service/user.service";
import {User} from "../user";
import {TokenStorageService} from "../../auth/token-storage.service";
import {Location} from "@angular/common";
import {AuthService} from "../../../service/auth.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthoritiesResponse} from "../../auth/authorities-response";
import {HandlingErrorsService} from "../../../service/handling-errors.service";
import {CustomValidators} from "../../../custom-validators";
import {ErrorHandler} from "../../error-handler";

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.scss']
})
export class UserUpdateComponent implements OnInit {
  isSaving: boolean;
  id: number;
  countries: {};
  authorities: AuthoritiesResponse[];
  error: ErrorHandler;

  userEdit = new FormGroup({
    authorities: new FormControl(null, [Validators.required]),
    email: new FormControl(null, Validators.compose([
      Validators.email,
      Validators.required
    ])),
    /*password: new FormControl(null, Validators.compose([
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
    ])),*/
    firstName: new FormControl(null, [Validators.required]),
    lastName: new FormControl(null, [Validators.required]),
    //gender: new FormControl(null, [Validators.required]),
    dateOfBirth: new FormControl(null, [Validators.required]),
    country: new FormControl(null, [Validators.required]),
    title: new FormControl(null, [Validators.required]),
    university: new FormControl(null, [Validators.required]),
    faculty: new FormControl(null, [Validators.required]),
    yearOfStudy: new FormControl(null, [Validators.required]),
    phoneNumber: new FormControl(null, /*CustomValidators.PhoneNumberValidator(this.getCountry())*/),
    //needVisa: new FormControl(null, [Validators.required]),
    passportNumber: new FormControl(null,
      CustomValidators.patternValidator(/^[_A-Za-z]{2}[0-9]{7}$/, {hasCapitalCase: true}))
  });

  private getCountry() {
    return this.userEdit.get('country').value();
  }

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private token: TokenStorageService,
    private location: Location,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.isSaving = false;
    this.id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUser(Number(this.id)).subscribe(user => {
      this.userEdit.patchValue(user);
      this.authorities = user.authoritiesSet;
    });
    this.authService.countryList().subscribe(data => this.countries = data);
  }

  isFieldValid(field: string) {
    return !this.userEdit.get(field).valid && this.userEdit.get(field).touched;
  }

  displayFieldCss(field: string) {
    return {
      'has-error': this.isFieldValid(field),
      'has-feedback': this.isFieldValid(field)
    };
  }

  previousState() {
    window.history.back();
  }

  checkErrorIsNotUndefined() {
    return this.error !== undefined;
  }

  save() {
    this.isSaving = true;
    /*if (!this.userEdit.value.needVisa)
      this.userEdit.value.passportNumber = "";*/
    if(this.userEdit.value.passportNumber !== ""){
      this.userEdit.value.needVisa = true;
    } else {
      this.userEdit.value.needVisa = false;
    }
    const user: User = this.userEdit.value;
    user.authoritiesSet = this.authorities;
    user.id = this.id;
    console.log(this.userEdit);
    console.log(user);
    if (this.userEdit.valid) {
      if (this.userEdit.value.id !== null) {
        console.log(user);
        this.userService.updateUser(this.token.getUserId(), user, "true").subscribe(response => {
          this.onSaveSuccess(response);
        }, error1 => {
          this.error = new ErrorHandler(error1.error.message);
          scroll(0,0)
          this.onSaveError();
        });
      }
    } else {
      this.validateAllFormFields(this.userEdit);
    }
  }

  private onSaveSuccess(result) {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }

  goBack(): void {
    this.location.back();
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
