import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {User} from "../../../../user/user";
import {UserService} from "../../../../../service/user.service";
import {ErrorHandler} from "../../../../error-handler";
import {compare, SortableHeaderDirective} from "../../../../../sortable/sortable-header.directive";
import {SortEvent} from "../../../../../sortable/sort-event";

@Component({
  selector: 'app-administrator-list',
  templateUrl: './administrator-list.component.html',
  styleUrls: ['./administrator-list.component.scss']
})
export class AdministratorListComponent implements OnInit {

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;
  private _users: User[];
  private usersCopy: User[];
  private _error: ErrorHandler;

  constructor(protected userService: UserService) { }

  ngOnInit() {
    this.userService.getAdmins().subscribe(users => {
      this._users = users;
      this.usersCopy = users;
    });
  }

  get users(): User[] {
    return this._users;
  }

  get error(): ErrorHandler {
    return this._error;
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

  onSortedUser({column, direction}: SortEvent) {
    this.headers.forEach(header => {
      if(header.sortable != column) {
        header.direction = '';
      }
    });
    if(direction === '') {
      this._users = this.usersCopy;
    } else {
      this._users = [...this.usersCopy].sort((a,b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

}
