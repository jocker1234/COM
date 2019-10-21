import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../user/user.service";

@Component({
  selector: 'app-single-send-mail',
  templateUrl: './single-send-mail.component.html',
  styleUrls: ['./single-send-mail.component.scss'],
})
export class SingleSendMailComponent implements OnInit {

  private mail: string;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {
    if (this.router.getCurrentNavigation().extras.state !== undefined) {
      this.mail = this.router.getCurrentNavigation().extras.state.mail;
      this.mailForm.get('to').setValue(this.mail);
    }
  }

  mailForm = new FormGroup({
    to: new FormControl('', [Validators.required, Validators.email]),
    subject: new FormControl('', [Validators.required]),
    content: new FormControl('', [Validators.required])
  });

  ngOnInit() {
  }

  isFieldValid(field: string) {
    return !this.mailForm.get(field).valid && this.mailForm.get(field).touched;
  }

  sendMail() {
    this.userService.sendSingleMail(this.mailForm.value).subscribe(value => {
      let id = +this.route.snapshot.paramMap.get('id');
      this.router.navigate(["/admin/user/" + id]);
    });
  }
}
