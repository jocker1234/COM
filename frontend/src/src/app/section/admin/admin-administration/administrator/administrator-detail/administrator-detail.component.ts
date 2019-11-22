import { Component, OnInit } from '@angular/core';
import {User} from "../../../../user/user";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../../../service/user.service";

@Component({
  selector: 'app-administrator-detail',
  templateUrl: './administrator-detail.component.html',
  styleUrls: ['./administrator-detail.component.scss']
})
export class AdministratorDetailComponent implements OnInit {

  private _user: User;
  protected id: number;

  constructor(protected activatedRouter: ActivatedRoute,
              protected userService: UserService) {
    this.getUser();
  }

  ngOnInit() {
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

}
