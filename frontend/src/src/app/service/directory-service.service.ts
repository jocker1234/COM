import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Dictionary} from "../section/admin/admin-administration/dictionary";

const apiUrl = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class DirectoryServiceService {

  private directoryUrl = apiUrl + 'directory';

  constructor(private http: HttpClient) { }

  getAllParameters(): Observable<Dictionary[]> {
    return this.http.get<Dictionary[]>(`${this.directoryUrl}`);
  }

  getOneParameter(id: number): Observable<Dictionary> {

    return this.http.get<Dictionary>(`${this.directoryUrl}/${id}`);
  }

  updateParametr(directory:Dictionary): Observable<Dictionary> {
    return this.http.get<Dictionary>(`${this.directoryUrl}`);
  }

}
