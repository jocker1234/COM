import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {UserService} from "../../../../service/user.service";
import {User} from "../../../user/user";
import {compare, SortableHeaderDirective} from "../../../../sortable/sortable-header.directive";
import {SortEvent} from "../../../../sortable/sort-event";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;
  private _users: User[];
  private usersCopy: User[];

  constructor(protected userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUsers().subscribe(users => {
      this._users = users;
      this.usersCopy = users;
    });
  }

  get users(): User[] {
    return this._users;
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
