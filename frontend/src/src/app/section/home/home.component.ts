import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../auth/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  info: any;

  constructor(private token: TokenStorageService) {
  }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      email: this.token.getEmail(),
      authorities: this.token.getAuthorities(),
      id: this.token.getUserId()
    };
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }

}
