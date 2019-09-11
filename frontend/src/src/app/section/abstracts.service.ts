import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {CaseAbstract} from "./abstracts/case-abstract";
import {HandlingErrorsService} from "../handling-errors.service";
import {ResearchAbstract} from "./abstracts/research-abstract";

const apiUrl = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class AbstractsService {

  private caseUrl = apiUrl + 'case';
  private researchUrl = apiUrl + 'research';

  constructor(private http: HttpClient) { }

  newCaseAbstract(caseAbstract: CaseAbstract): Observable<CaseAbstract> {
    return this.http.post<CaseAbstract>(`${this.caseUrl}`, caseAbstract)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getAllCaseAbstract(): Observable<CaseAbstract[]> {
    return this.http.get<CaseAbstract[]>(`${this.caseUrl}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getOneCaseAbstract(id: number): Observable<CaseAbstract> {
    return this.http.get<CaseAbstract>(`${this.caseUrl}/${id}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  saveCaseAbstract(id: number, caseAbstract: CaseAbstract) {
    return this.http.put(`${this.caseUrl}/${id}/save`, caseAbstract)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  /*sendCaseAbstract(id: number) {
    return this.http.put(`${this.caseUrl}/${id}/send`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }*/

  newResearchAbstract(caseAbstract: ResearchAbstract): Observable<ResearchAbstract> {
    return this.http.post<ResearchAbstract>(`${this.researchUrl}`, caseAbstract)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getAllResearchAbstract(): Observable<ResearchAbstract[]> {
    return this.http.get<ResearchAbstract[]>(`${this.researchUrl}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getOneResearchAbstract(id: number): Observable<ResearchAbstract> {
    return this.http.get<ResearchAbstract>(`${this.researchUrl}/${id}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  saveResearchAbstract(id: number, researchAbstract: ResearchAbstract) {
    return this.http.put(`${this.researchUrl}/${id}/save`, researchAbstract)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  /*sendResearchAbstract(id: number) {
    return this.http.put(`${this.researchUrl}/${id}/send`,)
      .pipe(catchError(HandlingErrorsService.handleError));
  }*/



}
