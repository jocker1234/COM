import { Component, OnInit } from '@angular/core';
import {ResearchAbstractItemsDetailsComponent} from "../../../../abstracts/research/research-abstract-items-details/research-abstract-items-details.component";
import {ActivatedRoute} from "@angular/router";
import {AbstractsService} from "../../../../../service/abstracts.service";

@Component({
  selector: 'app-admin-research-abstract-details',
  templateUrl: './admin-research-abstract-details.component.html',
  styleUrls: ['./admin-research-abstract-details.component.scss']
})
export class AdminResearchAbstractDetailsComponent extends ResearchAbstractItemsDetailsComponent implements OnInit {

  constructor(route: ActivatedRoute, abstractsService: AbstractsService) {
    super(route, abstractsService);
  }

  ngOnInit() {
  }

}
