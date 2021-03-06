import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AuthLoginInfo} from "../section/auth/login-info";
import {Observable} from "rxjs";
import {JwtResponse} from "../section/auth/jwt-response";
import {catchError} from "rxjs/operators";
import {HandlingErrorsService} from "./handling-errors.service";
import {SignUpInfo} from "../section/auth/signup-info";
import {environment} from "../../environments/environment";

const apiUrl = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = apiUrl + 'api/auth/signin';
  private signupUrl = apiUrl + 'api/auth/signup';
  private remaindPasswordUrl = apiUrl + 'api/auth/forgot';
  private countryListUrl = apiUrl + 'api/auth/countryList';
  private changePasswordUrl = apiUrl + 'api/auth/reset';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.loginUrl}`, credentials);
  }

  signUp(info: SignUpInfo): Observable<string> {
    const user: SignUpInfo = new SignUpInfo(info.email, info.password, info.firstName, info.lastName, /*info.gender,*/
      info.dateOfBirth, info.country, info.title, info.university, info.faculty, info.yearOfStudy, info.phoneNumber,
      /*info.needVisa*/info.passportNumber!==""&&info.passportNumber!==null, info.passportNumber, []);
    user.authorities.push(info.authorities.toString());
    return this.http.post<string>(`${this.signupUrl}`, user);
  }

  remaindPassword(email: string) {
    return this.http.post(`${this.remaindPasswordUrl}`, email)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  countryList() {
    return this.http.get(`${this.countryListUrl}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  changePassword(value: any, resetToken: string) {
    return this.http.patch(`${this.changePasswordUrl}`, {"password": value.password, "resetToken": resetToken});
  }
}
