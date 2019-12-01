import { Component, OnInit } from '@angular/core';
import {UsersListComponent} from "../users-list/users-list.component";
import {CategoryService} from "../../../../service/category.service";
import {AuthService} from "../../../../service/auth.service";
import {UserService} from "../../../../service/user.service";
import {Router} from "@angular/router";
import {ErrorHandler} from "../../../error-handler";

@Component({
  selector: 'app-check-multi-user-to-mail',
  templateUrl: './check-multi-user-to-mail.component.html',
  styleUrls: ['./check-multi-user-to-mail.component.scss']
})
export class CheckMultiUserToMailComponent  extends UsersListComponent implements OnInit {

  userCheck: any[] = [];

  constructor(protected categoryService: CategoryService, protected authService: AuthService,
              protected userService: UserService, private router: Router) {
    super(categoryService, authService, userService);
  }

  ngOnInit() {
    this.userService.getUsers(this.searchCriteria.value).subscribe(usersData => {
      this._users = usersData;
      this.usersCopy = usersData;
    });
    this.authService.countryList().subscribe(data => this._countries = data);
    this.categoryService.getCategory().subscribe(value => {
      this._categories = value;
    });
  }

  checkUser(id: number, email: string) {
    if(this.userCheck.find(x => x == id) === id) {
      const index: number = this.userCheck.indexOf(id);
      if (index !== -1) {
        this.userCheck.splice(index, 1);
      }
    } else {
      this.userCheck.push({'id': id, 'email': email});
    }
  }

  selectAllUser() {
    this.userCheck = [];
    for(let i = 0; i < this.users.length; i++) {
      let element = document.getElementById('checkbox-'+i) as HTMLElement;
      element.click()
    }
  }

  clickSendMail() {
    this.router.navigate(["/admin/user/group-mail/send"],{state: {mail: this.userCheck}});
  }

}
