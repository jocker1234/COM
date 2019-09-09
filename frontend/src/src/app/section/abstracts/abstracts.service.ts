import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {HandlingErrorsService} from "../../handling-errors.service";
import {CaseAbstract} from "./case-abstract";
import {ResearchAbstract} from "./research-abstract";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AbstractsService {

  private caseUrl = 'http://localhost:8080/case';
  private researchUrl = 'http://localhost:8080/research';

  constructor(private http: HttpClient) { }

  newCaseAbstract(caseAbstract: CaseAbstract): Observable<CaseAbstract> {
    return this.http.post<CaseAbstract>(`${this.caseUrl}`, caseAbstract, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getAllCaseAbstract(): Observable<CaseAbstract[]> {
    return this.http.get<CaseAbstract[]>(`${this.caseUrl}`, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getOneCaseAbstract(id: number): Observable<CaseAbstract> {
    return this.http.get<CaseAbstract>(`${this.caseUrl}/${id}`, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  saveCaseAbstract(id: number, caseAbstract: CaseAbstract) {
    return this.http.put(`${this.caseUrl}/${id}/save`, caseAbstract, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  sendCaseAbstract(id: number) {
    return this.http.put(`${this.caseUrl}/${id}/send`, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  newResearchAbstract(caseAbstract: ResearchAbstract): Observable<ResearchAbstract> {
    return this.http.post<ResearchAbstract>(`${this.researchUrl}`, caseAbstract, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getAllResearchAbstract(): Observable<ResearchAbstract[]> {
    return this.http.get<ResearchAbstract[]>(`${this.researchUrl}`, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getOneResearchAbstract(id: number): Observable<ResearchAbstract> {
    return this.http.get<ResearchAbstract>(`${this.researchUrl}/${id}`, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  saveResearchAbstract(id: number, researchAbstract: ResearchAbstract) {
    return this.http.put(`${this.researchUrl}/${id}/save`, researchAbstract, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  sendResearchAbstract(id: number) {
    return this.http.put(`${this.researchUrl}/${id}/send`, httpOptions)
      .pipe(catchError(HandlingErrorsService.handleError));
  }



}
