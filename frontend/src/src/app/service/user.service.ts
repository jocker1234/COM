import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {User} from "../section/user/user";
import {catchError, tap} from "rxjs/operators";
import {HandlingErrorsService} from "./handling-errors.service";
import {environment} from "../../environments/environment";

const apiUrl = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = apiUrl + 'api/user';

  constructor(private http: HttpClient) {
  }

  getUsers(searchCriteria: any): Observable<User[]> {
    let httpParams = new HttpParams({
      fromObject: {
        'country': searchCriteria.country,
        'university': searchCriteria.university,
        'title': searchCriteria.title,
        'yearOfStudy': searchCriteria.yearOfStudy,
        'status': searchCriteria.status,
        'typeAbstract': searchCriteria.type,
        'nameCategory': searchCriteria.category
      }
    });
    return this.http.get<any>(`${this.userUrl}`, {params: httpParams})
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getUserForAdmin(id: number): Observable<User> {
    return this.http.get<any>(`${this.userUrl}/${id}/admin`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.userUrl}/${id}`);
  }

  deleteUser(id: number) {
    return this.http.delete(`${this.userUrl}/${id}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  updateUser(id: number, user: User, isUpdate: string) {
    let httpParams = new HttpParams();
    httpParams.append("isUpdated", isUpdate);
    return this.http.put(`${this.userUrl}/${id}?isUpdated=true`, user);
  }

  sendSingleMail(mail: any): Observable<any> {
    return this.http.post<any>(`${this.userUrl}/single-mail`, mail)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  sortUsers(criteria: SearchCriteria, users: User[]): User[] {
    return users.sort((a,b) => {
      if(criteria.sortDirection === 'desc'){
        return a[criteria.sortColumn] - b[criteria.sortColumn];
      } else {
        return a[criteria.sortColumn] - b[criteria.sortColumn];
      }
    });
  }

  createAdmin(user: any) {
    return this.http.post<string>(`${this.userUrl}/create-admin`, user);
  }

  getAdmins() {
    return this.http.get<any>(`${this.userUrl}/admins`);
  }
}

export class SearchCriteria {
  sortColumn: string;
  sortDirection: string;
}
