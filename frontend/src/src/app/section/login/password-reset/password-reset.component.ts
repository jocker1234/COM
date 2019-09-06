import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.scss']
})
export class PasswordResetComponent implements OnInit {
  email: string;

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
  }

  remind() {
    console.log(this.email);
    this.authService.remaindPassword(this.email).subscribe(response => {
      console.log(response);
      this.router.navigate(['/login']);
    }, error => console.log(error));

  }
}
