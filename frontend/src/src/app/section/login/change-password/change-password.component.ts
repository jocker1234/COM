import {Component, OnInit} from '@angular/core';
import {ErrorHandler} from "../../error-handler";
import {AuthService} from "../../../service/auth.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../../../custom-validators";
import {Router} from "@angular/router";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  private _error: ErrorHandler;

  changePasswordForm = new FormGroup({
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
    confirmPassword: new FormControl()
  },{
    validators: this.checkPasswords
  });

  checkPasswords(group: FormGroup) {
    let password = group.get('password').value; // to get value in input tag
    let confirmPassword = group.get('confirmPassword').value; // to get value in input tag
    if(password != confirmPassword) {
      group.get('confirmPassword').setErrors( {MatchPassword: true} )
    } else {
      return null
    }
  }

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
  }

  get error(): ErrorHandler {
    return this._error;
  }

  isFieldValid(field: string) {
    return !this.changePasswordForm.get(field).valid && this.changePasswordForm.get(field).touched;
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
    let token = this.router.url.split('?')[1].split('=')[1];
    console.log(token);
    this.authService.changePassword(this.changePasswordForm.value, token).subscribe(
      data => {
      }, error1 => {
        this._error = new ErrorHandler(error1.error.message);
        scroll(0, 0)
      }
    );
    this.router.navigate(['/login']);
  }

}
