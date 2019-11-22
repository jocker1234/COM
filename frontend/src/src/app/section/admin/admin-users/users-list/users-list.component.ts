import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../../service/user.service";
import {User} from "../../../user/user";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {
  private _users: User[];

  constructor(protected userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUsers().subscribe(users => {
      this._users = this.userService.sortUsers({sortColumn: 'id', sortDirection:'asc'}, users);;
    });
  }

  get users(): User[] {
    return this._users;
  }

  onSorted($event) {
    this._users = this.userService.sortUsers($event, this.users);
  }
}
