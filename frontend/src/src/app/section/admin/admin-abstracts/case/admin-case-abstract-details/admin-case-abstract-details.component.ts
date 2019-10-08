import { Component, OnInit } from '@angular/core';
import {CaseAbstractItemsDetailsComponent} from "../../../../abstracts/case/case-abstract-items-details/case-abstract-items-details.component";
import {ActivatedRoute} from "@angular/router";
import {AbstractsService} from "../../../../abstracts.service";

@Component({
  selector: 'app-admin-case-abstract-details',
  templateUrl: './admin-case-abstract-details.component.html',
  styleUrls: ['./admin-case-abstract-details.component.scss']
})
export class AdminCaseAbstractDetailsComponent extends CaseAbstractItemsDetailsComponent implements OnInit {


  constructor(route: ActivatedRoute, abstractsService: AbstractsService) {
    super(route, abstractsService);
  }

  ngOnInit() {
  }

}
