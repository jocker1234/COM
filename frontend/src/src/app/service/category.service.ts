import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Category} from "../section/category";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {HandlingErrorsService} from "./handling-errors.service";

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

  createCategory(category: Category): Observable<Category[]> {
    return this.http.post<Category[]>(`${this.categoryUrl}`, category);
  }

  updateCategory(id: number, category: Category): Observable<Category[]> {
    return this.http.put<Category[]>(`${this.categoryUrl}/${id}`, category);
  }

  deleteCategory(id: number): Observable<Category[]> {
    return this.http.delete<Category[]>(`${this.categoryUrl}/${id}`);
  }

}
