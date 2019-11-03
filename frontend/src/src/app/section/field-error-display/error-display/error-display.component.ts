import {Component, Input, OnInit} from '@angular/core';
import {ErrorHandler} from "../../error-handler";

@Component({
  selector: 'app-error-display',
  templateUrl: './error-display.component.html',
  styleUrls: ['./error-display.component.scss']
})
export class ErrorDisplayComponent implements OnInit {

  @Input()
  private _error: ErrorHandler;
  @Input()
  private _displayError: boolean;


  get error(): ErrorHandler {
    return this._error;
  }

  set error(value: ErrorHandler) {
    this._error = value;
  }

  get displayError(): boolean {
    return this._displayError;
  }

  set displayError(value: boolean) {
    this._displayError = value;
  }

  constructor() { }

  ngOnInit() {
  }

}
