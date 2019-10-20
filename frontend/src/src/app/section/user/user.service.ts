import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {User} from "./user";
import {catchError, tap} from "rxjs/operators";
import {HandlingErrorsService} from "../../handling-errors.service";
import {environment} from "../../../environments/environment";
import {CaseAbstract} from "../abstracts/case-abstract";

const apiUrl = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = apiUrl + 'api/user';

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.http.get<any>(`${this.userUrl}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getUserForAdmin(id: number): Observable<User> {
    return this.http.get<any>(`${this.userUrl}/${id}/admin`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.userUrl}/${id}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  deleteUser(id: number) {
    return this.http.delete(`${this.userUrl}/${id}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  updateUser(id: number, user: User, isUpdate: string) {
    let httpParams = new HttpParams();
    httpParams.append("isUpdated", isUpdate);
    return this.http.put(`${this.userUrl}/${id}?isUpdated=true`, user)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  sendSingleMail(mail: any): Observable<any> {
    return this.http.post<any>(`${this.userUrl}/single-mail`, mail)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

}
