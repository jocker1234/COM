import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../service/auth.service";
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
    this.authService.remaindPassword(this.email).subscribe(response => {
      this.router.navigate(['/login']);
    });

  }
}
