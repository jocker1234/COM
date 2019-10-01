import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";
import {Injectable} from "@angular/core";

@Injectable({providedIn: "root"})
export class AuthGuard implements CanActivate {

  constructor(private router: Router,
              private tokenStorage: TokenStorageService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.tokenStorage.isTokenExpired()) {
      console.log(this.tokenStorage.isTokenExpired());
      const authorities = route.data['authorities'];
      if (!authorities || authorities === undefined) {
        return false;
      }
      let authoritiesName = this.tokenStorage.getAuthorities();
      for (let i = 0; i < authoritiesName.length; i++) {
        if (authoritiesName[i] === authorities[0]) {
          return true;
        }
      }
      this.router.navigate(['/login']);
      console.log(this.tokenStorage.isTokenExpired());
      return false;
    } else {
      this.tokenStorage.signOut();
      return false;
    }
  }


}
