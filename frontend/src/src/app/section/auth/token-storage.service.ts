import { Injectable } from '@angular/core';
import {AuthoritiesResponse} from "./authorities-response";
import * as jwt_decode from 'jwt-decode';

const TOKEN_KEY = 'AuthToken';
const TOKEN_EXPIRED = 'TokenExpired'
const EMAIL_KEY = 'AuthEmail';
const AUTHORITIES_KEY = 'AuthAuthorities';
const USERID_KEY = 'AuthUserId';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private roles: Array<string> = [];
  constructor() { }

  signOut() {
    if (window.localStorage.length !== 0) {
      window.localStorage.clear();
      window.location.replace('');
    }
  }

  public saveToken(accessToken: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, accessToken);
  }

  public getToken(): string {
    return localStorage.getItem(TOKEN_KEY);
  }

  private getTokenExpirationDate(token: String) {
    const decoded = jwt_decode(token);

    if(decoded.exp === undefined) {
      return null;
    }

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  public isTokenExpired(token?: string): boolean {
    if(!token) token = this.getToken();
    if(!token) return true;

    const date = this.getTokenExpirationDate(token);
    console.log(date);
    if(date === undefined) return false;
    return !(date.valueOf() > new Date().valueOf());
  }

  public saveEmail(email: string) {
    window.localStorage.removeItem(EMAIL_KEY);
    window.localStorage.setItem(EMAIL_KEY, email);
  }

  public getEmail(): string {
    return localStorage.getItem(EMAIL_KEY);
  }

  public saveAuthorities(authorities: AuthoritiesResponse) {
    window.localStorage.removeItem(AUTHORITIES_KEY);
    window.localStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];

    if (localStorage.getItem(TOKEN_KEY)) {
      JSON.parse(localStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
        this.roles.push(authority.authority);
      });
    }
    return this.roles;
  }

  public saveUserId(id: number) {
    window.localStorage.removeItem(USERID_KEY);
    window.localStorage.setItem(USERID_KEY, String(id));
  }

  public getUserId(): number {
    return Number(localStorage.getItem(USERID_KEY));
  }
}
