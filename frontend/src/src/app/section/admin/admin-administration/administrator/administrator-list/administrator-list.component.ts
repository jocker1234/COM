import { Component, OnInit } from '@angular/core';
import {User} from "../../../../user/user";
import {UserService} from "../../../../../service/user.service";
import {ErrorHandler} from "../../../../error-handler";

@Component({
  selector: 'app-administrator-list',
  templateUrl: './administrator-list.component.html',
  styleUrls: ['./administrator-list.component.scss']
})
export class AdministratorListComponent implements OnInit {

  private _users: User[];
  private _error: ErrorHandler;

  constructor(protected userService: UserService) { }

  ngOnInit() {
    this.userService.getAdmins().subscribe(users => {
      this._users = this.userService.sortUsers({sortColumn: 'id', sortDirection:'asc'}, users);;
    });
  }

  get users(): User[] {
    return this._users;
  }

  onSorted($event) {
    this._users = this.userService.sortUsers($event, this.users);
  }

  get error(): ErrorHandler {
    return this._error;
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

}
