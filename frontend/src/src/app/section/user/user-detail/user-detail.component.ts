import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../service/user.service";
import {User} from "../user";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from '@angular/common';
import {TokenStorageService} from "../../auth/token-storage.service";
import {ErrorHandler} from "../../error-handler";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
  protected _user: User;
  private _id: number;
  protected _error: ErrorHandler;

  constructor(protected activatedRouter: ActivatedRoute,
              protected userService: UserService,
              private location: Location,
              private tokenStorage: TokenStorageService,
              private router: Router) {
    this.getUser();
  }

  ngOnInit() {
  }

  protected getUser() {
    this._id = +this.activatedRouter.snapshot.paramMap.get('id');
    this.userService.getUser(Number(this._id)).subscribe(user => {
      this._user = user
    }, error1 => {
      this._error = new ErrorHandler(error1.error.message);
      scroll(0,0)
    });
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

  get id(): number {
    return this._id;
  }

  get error(): ErrorHandler {
    return this._error;
  }

  get user(): User {
    return this._user;
  }

  goBack(): void {
    this.location.back();
  }
}
