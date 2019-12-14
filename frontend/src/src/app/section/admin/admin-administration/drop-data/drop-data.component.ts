import { Component, OnInit } from '@angular/core';
import {CaseAbstract} from "../../../abstracts/case-abstract";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "../../../auth/token-storage.service";
import * as fileSaver from 'file-saver';
import {UserService} from "../../../../service/user.service";
import {AbstractsService} from "../../../../service/abstracts.service";
import {Category} from "../../../category";
import {ErrorHandler} from "../../../error-handler";

@Component({
  selector: 'app-drop-data',
  templateUrl: './drop-data.component.html',
  styleUrls: ['./drop-data.component.scss']
})
export class DropDataComponent implements OnInit {

  protected _error: ErrorHandler;

  constructor(private http: HttpClient,private token: TokenStorageService, private userService: UserService,
              private abstractsService: AbstractsService) { }

  ngOnInit() {
  }

  get error(): ErrorHandler {
    return this._error;
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

  dropUsers() {
    this.userService.dropAllUsers().subscribe(value => {

    }, error1 => {
      this._error = new ErrorHandler(error1.error.message);
      scroll(0,0);
    });
  }

  dropAbstracts() {
    this.abstractsService.dropAllAbstracts().subscribe(value => {

    }, error1 => {
      this._error = new ErrorHandler(error1.error.message);
      scroll(0,0)
    });
  }

  dropAllData() {
    this.dropUsers();
    this.dropAbstracts();
  }
}
