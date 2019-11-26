import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../section/auth/token-storage.service";
import {DictionaryStorageService} from "../service/dictionary-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  private _roles: string[];
  private _place: string;
  private _time: string;

  constructor(private _tokenStorage: TokenStorageService, private dictionaryStorage: DictionaryStorageService) {
    this.setPlace();
    this.setTime();
  }

  ngOnInit() {
    if (this._tokenStorage.getToken()) {
      this._roles = this._tokenStorage.getAuthorities();
    }
  }


  get place(): string {
    return this._place;
  }

  setPlace() {
    this._place = this.dictionaryStorage.getLocation();
  }

  get time(): string {
    return this._time;
  }

  setTime() {
    this._time = this.dictionaryStorage.getTime();
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
