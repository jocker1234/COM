import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Category} from "./category";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {HandlingErrorsService} from "../handling-errors.service";

const apiUrl = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private categoryUrl = apiUrl + 'category';

  constructor(private http: HttpClient) { }

  getCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.categoryUrl}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.categoryUrl}/${id}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

}
