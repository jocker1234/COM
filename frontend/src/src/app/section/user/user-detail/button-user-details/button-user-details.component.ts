import {Component, Input, OnInit} from '@angular/core';
import {TokenStorageService} from "../../../auth/token-storage.service";
import {UserService} from "../../user.service";

@Component({
  selector: 'app-button-user-details',
  templateUrl: './button-user-details.component.html',
  styleUrls: ['./button-user-details.component.scss']
})
export class ButtonUserDetailsComponent implements OnInit {

  @Input()
  private id: number;

  constructor(private userService: UserService, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
  }

  delete() {
    this.userService.deleteUser(this.id).subscribe(value => {
      this.tokenStorage.signOut();
    });
  }

}
