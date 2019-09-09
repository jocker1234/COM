import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AuthLoginInfo} from "./login-info";
import {Observable} from "rxjs";
import {JwtResponse} from "./jwt-response";
import {catchError} from "rxjs/operators";
import {HandlingErrorsService} from "../../handling-errors.service";
import {SignUpInfo} from "./signup-info";
import {environment} from "../../../environments/environment";

const apiUrl = environment.apiUrl;

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
    return this.http.post<JwtResponse>(`${this.loginUrl}`, credentials)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(`${this.signupUrl}`, info)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  remaindPassword(email: string){
    return this.http.post(`${this.remaindPasswordUrl}`, email)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  countryList(){
    return this.http.get(`${this.countryListUrl}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

}
