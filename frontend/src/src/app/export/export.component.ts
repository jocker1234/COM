import { Component, OnInit } from '@angular/core';
import {ExportFile} from "./export-file";
import {ErrorHandler} from "../section/error-handler";
import {SuccessfulHandler} from "../section/successful-handler";

@Component({
  selector: 'app-export',
  templateUrl: './export.component.html',
  styleUrls: ['./export.component.scss']
})
export class ExportComponent implements OnInit {

  protected _export: ExportFile = new ExportFile();
  private _error: ErrorHandler;
  private _successful: SuccessfulHandler;

  constructor() { }

  ngOnInit() {
  }

  get export(): ExportFile {
    return this._export;
  }

  set error(value: ErrorHandler) {
    this._error = value;
  }

  get error(): ErrorHandler {
    return this._error;
  }

  get successful(): SuccessfulHandler {
    return this._successful;
  }

  set successful(value: SuccessfulHandler) {
    this._successful = value;
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

  checkSuccessfulIsNotUndefined() {
    return this._successful !== undefined;
  }

  onSubmit() {
  }

}
