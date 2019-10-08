import { Component, OnInit } from '@angular/core';
import {CaseAbstract} from "../../abstracts/case-abstract";
import {ResearchAbstract} from "../../abstracts/research-abstract";
import {AbstractsService} from "../../abstracts.service";

@Component({
  selector: 'app-admin-abstracts',
  templateUrl: './admin-abstracts.component.html',
  styleUrls: ['./admin-abstracts.component.scss']
})
export class AdminAbstractsComponent implements OnInit {

  private case: CaseAbstract[];
  private research: ResearchAbstract[];

  constructor(private abstractService: AbstractsService) { }

  ngOnInit() {
    this.abstractService.getAllCaseAbstract().subscribe(value => {
      this.case = value;
      console.log(value);
    });
    this.abstractService.getAllResearchAbstract().subscribe(value => {
      this.research = value;
      console.log(value);
    });
  }

}
