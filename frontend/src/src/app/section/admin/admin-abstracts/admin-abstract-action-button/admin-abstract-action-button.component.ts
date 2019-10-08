import {Component, Input, OnInit} from '@angular/core';
import {ResearchAbstract} from "../../../abstracts/research-abstract";
import {CaseAbstract} from "../../../abstracts/case-abstract";
import {AbstractsService} from "../../../abstracts.service";
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

  constructor(private abstractService: AbstractsService, private router: Router,
              private location: Location) {
  }

  ngOnInit() {
  }

  approved() {
    if (this.prefixUrl.includes("case") === true) {
      this.abstractService.rejectionApprovedCase(this.id, "A").subscribe(value => {

      });
    } else if (this.prefixUrl.includes("research") === true) {
      this.abstractService.rejectionApprovedResearch(this.id, "A").subscribe(value => {

      });
    }
  }

  rejected() {
    if (this.prefixUrl.includes("case") === true) {
      this.abstractService.rejectionApprovedCase(this.id, "R").subscribe(value => {

      });
    } else if (this.prefixUrl.includes("research") === true) {
      this.abstractService.rejectionApprovedResearch(this.id, "R").subscribe(value => {

      });
    }
  }


}
