import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {Directory} from "../section/admin/admin-administration/directory";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const apiUrl = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class DirectoryServiceService {

  private directoryUrl = apiUrl + 'directory';

  constructor(private http: HttpClient) { }

  getAllParameters(): Observable<Directory[]> {
    return this.http.get<Directory[]>(`${this.directoryUrl}`);
  }

  getOneParameter(id: number): Observable<Directory> {

    return this.http.get<Directory>(`${this.directoryUrl}/${id}`);
  }

  updateParametr(directory:Directory): Observable<Directory> {
    return this.http.get<Directory>(`${this.directoryUrl}`);
  }

}
