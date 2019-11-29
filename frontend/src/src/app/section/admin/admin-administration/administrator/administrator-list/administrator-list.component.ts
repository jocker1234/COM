import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {User} from "../../../../user/user";
import {UserService} from "../../../../../service/user.service";
import {ErrorHandler} from "../../../../error-handler";
import {compare, SortableHeaderDirective} from "../../../../../sortable/sortable-header.directive";
import {SortEvent} from "../../../../../sortable/sort-event";
import {faAngleDown, faAngleUp} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-administrator-list',
  templateUrl: './administrator-list.component.html',
  styleUrls: ['./administrator-list.component.scss']
})
export class AdministratorListComponent implements OnInit {

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;
  private _admins: User[];
  private adminsCopy: User[];
  private _error: ErrorHandler;
  private keySort = undefined;
  private reverseSort = undefined;
  faAngleDown = faAngleDown;
  faAngleUp = faAngleUp;

  constructor(protected userService: UserService) { }

  ngOnInit() {
    this.userService.getAdmins().subscribe(users => {
      this._admins = users;
      this.adminsCopy = users;
    });
  }

  get admins(): User[] {
    return this._admins;
  }

  get error(): ErrorHandler {
    return this._error;
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

  onSortedAdmin({column, direction}: SortEvent) {
    console.log(column)
    console.log(direction)
    this.keySort = column;
    this.reverseSort = direction === '' ? undefined : direction === 'asc' ? true : false;
    this.headers.forEach(header => {
      if(header.sortable != column) {
        header.direction = '';
      }
    });
    if(direction === '') {
      this._admins = this.adminsCopy;
    } else {
      this._admins = [...this.adminsCopy].sort((a,b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

}
