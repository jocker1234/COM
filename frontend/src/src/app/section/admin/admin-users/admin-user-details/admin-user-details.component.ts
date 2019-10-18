import { Component, OnInit } from '@angular/core';
import {UserDetailComponent} from "../../../user/user-detail/user-detail.component";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../user/user.service";
import {TokenStorageService} from "../../../auth/token-storage.service";
import {Location} from '@angular/common';

@Component({
  selector: 'app-admin-user-details',
  templateUrl: './../../../user/user-detail/user-detail.component.html',
  styleUrls: ['./admin-user-details.component.scss']
})
export class AdminUserDetailsComponent extends UserDetailComponent implements OnInit {

  constructor(activatedRouter: ActivatedRoute, userService: UserService, location: Location,
              tokenStorage: TokenStorageService, router: Router) {
    super(activatedRouter, userService, location, tokenStorage, router);
  }

  ngOnInit() {
  }

}
