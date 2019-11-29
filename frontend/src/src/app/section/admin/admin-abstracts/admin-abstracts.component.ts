import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {CaseAbstract} from "../../abstracts/case-abstract";
import {ResearchAbstract} from "../../abstracts/research-abstract";
import {AbstractsService} from "../../../service/abstracts.service";
import {compare, SortableHeaderDirective} from "../../../sortable/sortable-header.directive";
import {SortEvent} from "../../../sortable/sort-event";
import {faAngleDown, faAngleUp} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-admin-abstracts',
  templateUrl: './admin-abstracts.component.html',
  styleUrls: ['./admin-abstracts.component.scss']
})
export class AdminAbstractsComponent implements OnInit {

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;
  private _case: CaseAbstract[];
  private _research: ResearchAbstract[];
  private caseCopy: CaseAbstract[];
  private researchCopy: ResearchAbstract[];
  private _keySortC = undefined;
  private _reverseSortC = undefined;
  private _keySortR = undefined;
  private _reverseSortR = undefined;
  faAngleDown = faAngleDown;
  faAngleUp = faAngleUp;

  constructor(private abstractService: AbstractsService) { }

  ngOnInit() {
    this.abstractService.getAllCaseAbstract().subscribe(value => {
      this._case = value;
      this.caseCopy = value;
    });
    this.abstractService.getAllResearchAbstract().subscribe(value => {
      this._research = value;
      this.researchCopy = value;
    });
  }

  get case(): CaseAbstract[] {
    return this._case;
  }

  get research(): ResearchAbstract[] {
    return this._research;
  }


  get keySortC(): any {
    return this._keySortC;
  }

  get keySortR(): any {
    return this._keySortR;
  }

  get reverseSortC(): any {
    return this._reverseSortC;
  }

  get reverseSortR(): any {
    return this._reverseSortR;
  }

  onSortedCase({column, direction}: SortEvent){
    this._keySortC = column;
    this._reverseSortC = direction === '' ? undefined : direction === 'asc' ? true : false;
    this.headers.forEach(header => {
      if(header.sortable != column) {
        header.direction = '';
      }
    });
    if(direction === '') {
      this._case = this.caseCopy;
    } else {
      this._case = [...this.caseCopy].sort((a,b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  onSortedResearch({column, direction}: SortEvent) {
    this._keySortR = column;
    this._reverseSortC = direction === '' ? undefined : direction === 'asc' ? true : false;
    this.headers.forEach(header => {
      if(header.sortable != column) {
        header.direction = '';
      }
    });
    if(direction === '') {
      this._research = this.researchCopy;
    } else {
      this._research = [...this.researchCopy].sort((a,b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }
}
