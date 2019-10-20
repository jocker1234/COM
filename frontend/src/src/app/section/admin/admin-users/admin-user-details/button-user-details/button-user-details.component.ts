import {Component, Input, OnInit} from '@angular/core'
import {User} from "../../../../user/user";
import {Md5} from "ts-md5";
import {Router} from "@angular/router";

@Component({
  selector: 'app-button-user-details',
  templateUrl: './button-user-details.component.html',
  styleUrls: ['./button-user-details.component.scss']
})
export class ButtonUserDetailsComponent implements OnInit {

  @Input()
  private id: number;

  @Input()
  user: User;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  clickSendMail() {
    this.router.navigate(["/user/" + this.id + "/send-mail"],{state: {mail:this.user.email}});
  }
}
