import {AfterViewInit, Component, ElementRef, OnInit, ViewChild, ViewChildren} from '@angular/core';
import {UserDetailComponent} from "../../../user/user-detail/user-detail.component";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../../service/user.service";
import {User} from "../../../user/user";
import {ErrorHandler} from "../../../error-handler";

@Component({
  selector: 'app-admin-user-details',
  templateUrl: './admin-user-details.component.html',
  styleUrls: ['./admin-user-details.component.scss']
})
export class AdminUserDetailsComponent implements OnInit  {
  private _user: User;
  protected id: number;
  private _error: ErrorHandler;

  constructor(protected activatedRouter: ActivatedRoute,
              protected userService: UserService) {
    this.getUser();
  }

  get user(): User {
    return this._user;
  }

  protected getUser() {
    this.id = +this.activatedRouter.snapshot.paramMap.get('id');
    this.userService.getUserForAdmin(Number(this.id)).subscribe(user => {
      this._user = user;
    });
  }

  ngOnInit() {

  }

  get error(): ErrorHandler {
    return this._error;
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

}
