import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../user/user.service";
import {User} from "../../../user/user";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {
  protected users: User[];

  constructor(protected userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }

}
