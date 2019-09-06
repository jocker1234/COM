import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable, of, throwError} from "rxjs";
import {SignUpInfo} from "./signup-info";
import {AuthLoginInfo} from "./login-info";
import {JwtResponse} from "./jwt-response";
import {catchError} from "rxjs/operators";
import {HandlingErrorsService} from "../../handling-errors.service";
import {environment} from "../../../environments/environment";

const apiUrl = environment.apiUrl;

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = apiUrl + 'api/auth/signin';
  private signupUrl = apiUrl + 'api/auth/signup';
  private remaindPasswordUrl = apiUrl + 'api/auth/reset';
  private countryListUrl = apiUrl + 'api/auth/countryList';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.loginUrl}`, credentials, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(`${this.signupUrl}`, info, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  remaindPassword(email: string){
    return this.http.post(`${this.remaindPasswordUrl}`, email, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  countryList(){
    return this.http.get(`${this.countryListUrl}`, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

}
