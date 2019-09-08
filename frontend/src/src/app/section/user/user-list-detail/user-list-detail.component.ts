import {Component, OnInit} from '@angular/core';
import {UserService} from "../user.service";
import {User} from "../user";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from '@angular/common';
import {TokenStorageService} from "../../auth/token-storage.service";

@Component({
  selector: 'app-user-list-detail',
  templateUrl: './user-list-detail.component.html',
  styleUrls: ['./user-list-detail.component.css']
})
export class UserListDetailComponent implements OnInit {
  user: User;
  id: number;

  constructor(private activatedRouter: ActivatedRoute,
              private userService: UserService,
              private location: Location,
              private tokenStorage: TokenStorageService,
              private router: Router) {
  }

  ngOnInit() {
    this.getUser();
  }

  private getUser() {
    this.id = +this.activatedRouter.snapshot.paramMap.get('id');
    this.userService.getUser(Number(this.id)).subscribe(user => {
      this.user = user
    });
  }

  delete() {
    this.userService.deleteUser(this.id);
    this.tokenStorage.signOut();
  }

  goBack(): void {
    this.location.back();
  }
}
