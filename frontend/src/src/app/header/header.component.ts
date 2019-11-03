import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../section/auth/token-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  private _roles: string[];

  constructor(private _tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    if (this._tokenStorage.getToken()) {
      this._roles = this._tokenStorage.getAuthorities();
    }
  }

  get roles(): string[] {
    return this._roles;
  }

  get tokenStorage(): TokenStorageService {
    return this._tokenStorage;
  }

  checkAuthorities(role: string): boolean {
    const findRole = this._roles.find(value => value == role);
    if (findRole != undefined) {
      return true;
    }
    return false;
  }

  logout() {
    this._tokenStorage.signOut();
  }

}
