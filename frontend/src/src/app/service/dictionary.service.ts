import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Dictionary} from "../section/admin/admin-administration/dictionary";

const apiUrl = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class DictionaryService {

  private dictionaryUrl = apiUrl + 'api/dictionary';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Dictionary[]> {
    return this.http.get<Dictionary[]>(`${this.dictionaryUrl}`);
  }

  getOne(id: number): Observable<Dictionary> {
    return this.http.get<Dictionary>(`${this.dictionaryUrl}/${id}`);
  }

  update(id: number, dictionary: Dictionary): Observable<Dictionary> {
    return this.http.put<Dictionary>(`${this.dictionaryUrl}/${id}`, dictionary);
  }

  updateImage(id: number, file:any): Observable<Dictionary> {
    return this.http.put<Dictionary>(`${this.dictionaryUrl}/${id}/image`, file);
  }

  sortValue(criteria: SearchCriteria, users: Dictionary[]): Dictionary[] {
    return users.sort((a,b) => {
      if(criteria.sortDirection === 'desc'){
        return a[criteria.sortColumn] - b[criteria.sortColumn];
      } else {
        return a[criteria.sortColumn] - b[criteria.sortColumn];
      }
    });
  }

  findByKey(key: string):Observable<string> {
    return this.http.get<string>(`${this.dictionaryUrl}/key/${key}`, {responseType: 'json'});
  }
}

export class SearchCriteria {
  sortColumn: string;
  sortDirection: string;
}
