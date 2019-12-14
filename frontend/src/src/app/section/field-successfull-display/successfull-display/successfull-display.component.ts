import {Component, Input, OnInit} from '@angular/core';
import {SuccessfulHandler} from "../../successful-handler";

@Component({
  selector: 'app-successfull-display',
  templateUrl: './successfull-display.component.html',
  styleUrls: ['./successfull-display.component.scss']
})
export class SuccessfullDisplayComponent implements OnInit {

  @Input()
  private _successful: SuccessfulHandler;
  @Input()
  private _displaySuccessful: boolean;

  constructor() { }

  ngOnInit() {
  }

  get successful(): SuccessfulHandler {
    return this._successful;
  }

  set successful(value: SuccessfulHandler) {
    this._successful = value;
  }

  get displaySuccessful(): boolean {
    return this._displaySuccessful;
  }

  set displaySuccessful(value: boolean) {
    this._displaySuccessful = value;
  }
}
