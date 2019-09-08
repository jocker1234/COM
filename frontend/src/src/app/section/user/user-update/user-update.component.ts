import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {User} from "../user";
import {TokenStorageService} from "../../auth/token-storage.service";
import {Location} from "@angular/common";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {
  user: User;
  isSaving: boolean;
  id: number;
  countries: {};

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private token: TokenStorageService,
    private location: Location,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.isSaving = false;
    this.id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUser(Number(this.id)).subscribe(user => {
      this.user = new User(user);
    });
    this.authService.countryList().subscribe(data => this.countries = data);
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if(!this.user.needVisa)
      this.user.passportNumber="";
    if (this.user.id !== null) {
      this.userService.updateUser(this.token.getUserId(), this.user, "true").subscribe(response => {
        this.onSaveSuccess(response);
      }, error => {
        this.onSaveError();
      });
    }
  }

  private onSaveSuccess(result) {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }

  goBack(): void {
    this.location.back();
  }

}
