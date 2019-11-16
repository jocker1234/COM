import {Component, Input, OnInit} from '@angular/core';
import {AbstractsService} from "../../../../service/abstracts.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-admin-abstract-action-button',
  templateUrl: './admin-abstract-action-button.component.html',
  styleUrls: ['./admin-abstract-action-button.component.scss']
})
export class AdminAbstractActionButtonComponent implements OnInit {

  @Input()
  prefixUrl: string;

  @Input()
  private id: number;

  @Input()
  private _status: string;

  constructor(private abstractService: AbstractsService, private router: Router,
              private location: Location) {
  }

  ngOnInit() {
  }

  get status(): string {
    return this._status;
  }

  checkStatus() {
    if(this.status === 'S' || this.status === 'A' || this.status === 'R') {
      return true;
    } else {
      return false;
    }
  }

  approved() {
    if (this.prefixUrl.includes("case") === true) {
      this.abstractService.rejectionApprovedCase(this.id, "A").subscribe(value => {
        window.location.reload();
      });
    } else if (this.prefixUrl.includes("research") === true) {
      this.abstractService.rejectionApprovedResearch(this.id, "A").subscribe(value => {
        window.location.reload();
      });
    }
  }

  rejected() {
    if (this.prefixUrl.includes("case") === true) {
      this.abstractService.rejectionApprovedCase(this.id, "R").subscribe(value => {
        window.location.reload();
      });
    } else if (this.prefixUrl.includes("research") === true) {
      this.abstractService.rejectionApprovedResearch(this.id, "R").subscribe(value => {
        window.location.reload();
      });
    }
  }


}
