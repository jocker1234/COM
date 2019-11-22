import { Component, OnInit } from '@angular/core';
import {Dictionary} from "../../dictionary";
import {ErrorHandler} from "../../../../error-handler";
import {DictionaryService} from "../../../../../service/dictionary.service";

@Component({
  selector: 'app-dictionary-list',
  templateUrl: './dictionary-list.component.html',
  styleUrls: ['./dictionary-list.component.scss']
})
export class DictionaryListComponent implements OnInit {

  private _dictionaries: Dictionary[];
  private _error: ErrorHandler;

  constructor(private dictionaryService: DictionaryService) { }

  ngOnInit() {
    this.dictionaryService.getAll().subscribe(value => {
      this._dictionaries = this.dictionaryService.sortValue({sortColumn: 'id', sortDirection:'asc'},value);
    });
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

  get dictionaries(): Dictionary[] {
    return this._dictionaries;
  }

  get error(): ErrorHandler {
    return this._error;
  }

  onSorted($event) {
    this._dictionaries = this.dictionaryService.sortValue($event, this.dictionaries);
  }

}
