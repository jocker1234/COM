import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {CaseAbstract} from "../section/abstracts/case-abstract";
import {HandlingErrorsService} from "./handling-errors.service";
import {ResearchAbstract} from "../section/abstracts/research-abstract";
import {Abstract} from "../section/abstracts/abstract";

const apiUrl = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class AbstractsService {

  private abstractUrl = apiUrl + 'abstracts';
  private caseUrl = this.abstractUrl + '/case';
  private researchUrl = this.abstractUrl + '/research';

  constructor(private http: HttpClient) {
  }

  getAllUserAbstracts(): Observable<Abstract[]> {
    return this.http.get<Abstract[]>(`${this.abstractUrl}/allUserAbstracts`).pipe(catchError(HandlingErrorsService.handleError));
  }

  newCaseAbstract(caseAbstract: CaseAbstract): Observable<CaseAbstract> {
    return this.http.post<CaseAbstract>(`${this.caseUrl}`, caseAbstract)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getAllCaseAbstract(): Observable<CaseAbstract[]> {
    return this.http.get<CaseAbstract[]>(`${this.caseUrl}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getOneCaseAbstract(id: number): Observable<CaseAbstract> {
    return this.http.get<CaseAbstract>(`${this.caseUrl}/${id}`);
  }

  saveCaseAbstract(id: number, caseAbstract: CaseAbstract): Observable<CaseAbstract> {
    return this.http.put<CaseAbstract>(`${this.caseUrl}/${id}/save`, caseAbstract)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  sendCaseAbstract(id: number) {
    return this.http.put(`${this.caseUrl}/${id}/send`, null)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  newResearchAbstract(caseAbstract: ResearchAbstract): Observable<ResearchAbstract> {
    return this.http.post<ResearchAbstract>(`${this.researchUrl}`, caseAbstract)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getAllResearchAbstract(): Observable<ResearchAbstract[]> {
    return this.http.get<ResearchAbstract[]>(`${this.researchUrl}`)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  getOneResearchAbstract(id: number): Observable<ResearchAbstract> {
    return this.http.get<ResearchAbstract>(`${this.researchUrl}/${id}`);
  }

  saveResearchAbstract(id: number, researchAbstract: ResearchAbstract): Observable<ResearchAbstract> {
    return this.http.put<ResearchAbstract>(`${this.researchUrl}/${id}/save`, researchAbstract)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  sendResearchAbstract(id: number) {
    return this.http.put(`${this.researchUrl}/${id}/send`, null)
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  deleteCaseAbstract(id: number) {
    return this.http.delete(`${this.caseUrl}/${id}`).pipe(catchError(HandlingErrorsService.handleError));
  }

  deleteResearchAbstract(id: number) {
    return this.http.delete(`${this.researchUrl}/${id}`).pipe(catchError(HandlingErrorsService.handleError));
  }

  rejectionApprovedCase(id: number, status: string) {
    return this.http.patch(`${this.caseUrl}/${id}/rejectApprove`, {"status": status})
      .pipe(catchError(HandlingErrorsService.handleError));
  }

  rejectionApprovedResearch(id: number, status: string) {
    return this.http.patch(`${this.researchUrl}/${id}/rejectApprove`, {"status": status})
      .pipe(catchError(HandlingErrorsService.handleError));
  }
}
