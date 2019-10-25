import {Component, OnInit} from '@angular/core';
import {UserService} from "../user.service";
import {User} from "../user";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from '@angular/common';
import {TokenStorageService} from "../../auth/token-storage.service";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
  private _user: User;
  protected id: number;

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
    this.id = +this.activatedRouter.snapshot.paramMap.get('id');
    this.userService.getUser(Number(this.id)).subscribe(user => {
      this._user = user
    });
  }


  get user(): User {
    return this._user;
  }

  goBack(): void {
    this.location.back();
  }
}
