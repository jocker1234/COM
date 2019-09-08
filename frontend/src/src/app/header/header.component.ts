import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../section/auth/token-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  private roles: string[];

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  checkAuthorities(role: string) {
    if (this.tokenStorage.getToken()) {
      this.roles.forEach(value => {
        if (value === role) {
          return true;
        }
      });
      return false;
    }
  }


  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }

}
