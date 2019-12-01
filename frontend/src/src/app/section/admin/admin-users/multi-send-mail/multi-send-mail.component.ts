import {Component, ElementRef, OnInit} from '@angular/core';
import {UsersListComponent} from "../users-list/users-list.component";
import {CategoryService} from "../../../../service/category.service";
import {UserService} from "../../../../service/user.service";
import {AuthService} from "../../../../service/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {ErrorHandler} from "../../../error-handler";

@Component({
  selector: 'app-multi-send-mail',
  templateUrl: './multi-send-mail.component.html',
  styleUrls: ['./multi-send-mail.component.scss']
})
export class MultiSendMailComponent implements OnInit {

  private _group: any[];
  private _mailGroup: string[] = []
  private _error: ErrorHandler;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {
    if (this.router.getCurrentNavigation().extras.state !== undefined) {
      this._group = this.router.getCurrentNavigation().extras.state.mail;
      console.log(this.group[0].email);
      for (let i = 0; i < this.group.length; i++) {
        this._mailGroup.push(this.group[i].email);
      }
      this.mailForm.get('multiTo').setValue(this.mailGroup);
    }
  }

  mailForm = new FormGroup({
    multiTo: new FormControl([], [Validators.required]),
    subject: new FormControl('', [Validators.required]),
    content: new FormControl('', [Validators.required])
  });


  get group(): any[] {
    return this._group;
  }

  get mailGroup(): string[] {
    return this._mailGroup;
  }

  ngOnInit() {
  }

  isFieldValid(field: string) {
    return !this.mailForm.get(field).valid && this.mailForm.get(field).touched;
  }

  sendMail() {
    this.userService.sendGroupMail(this.mailForm.value).subscribe(value => {
      this.router.navigate(["/admin/user/"]);
    });
  }

  get error(): ErrorHandler {
    return this._error;
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

}
