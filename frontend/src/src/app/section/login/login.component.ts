import {Component, OnInit} from '@angular/core';

import {AuthService} from '../../service/auth.service';
import {TokenStorageService} from '../auth/token-storage.service';
import {AuthLoginInfo} from '../auth/login-info';
import {JwtResponse} from '../auth/jwt-response';
import {Router} from "@angular/router";
import {ErrorHandler} from "../error-handler";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
  error: ErrorHandler;

  constructor(private authService: AuthService,
              private router: Router,
              private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  checkErrorIsNotUndefined() {
    return this.error !== undefined;
  }

  onSubmit() {
    this.loginInfo = new AuthLoginInfo(
      this.form.email,
      this.form.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      (data: JwtResponse) => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveEmail(data.email);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.tokenStorage.saveUserId(data.userId);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
      },
      error1 => {
        this.error = new ErrorHandler(error1.error.message);
        scroll(0,0)
        this.isLoginFailed = true;
      }
    );
    this.router.navigate(['..']);
  }

  navigate() {
    if (this.checkRole(this.roles, 'ROLE_ADMIN')) {
      this.router.navigateByUrl('/admin/abstract');
    } else if (this.checkRole(this.roles, 'ROLE_ACTIVE_PARTICIPANT')) {
      this.router.navigateByUrl('/abstracts');
    } else if (this.checkRole(this.roles,'ROLE_PASSIVE_PARTICIPANT')) {
      this.router.navigate(['/user/' + this.tokenStorage.getUserId()]);
    }
  }

  checkRole(listRole: string[], role: string) {
    for (let i = 0; i < listRole.length; i++) {
      if (listRole[i] === role) {
        return true;
      }
    }
  }

  reloadPage() {
    window.location.reload();
  }
}
