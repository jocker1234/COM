import {Component, OnInit} from "@angular/core";
import {CaseAbstract} from "../../case-abstract";
import {ActivatedRoute} from "@angular/router";
import {AbstractsService} from "../../../abstracts.service";

@Component({
  selector: 'app-case-abstract-items-details',
  templateUrl: './case-abstract-items-details.component.html',
  styleUrls: ['./case-abstract-items-details.component.scss']
})
export class CaseAbstractItemsDetailsComponent implements OnInit {

  abstract: CaseAbstract = new CaseAbstract();
  id: number;

  constructor(private route: ActivatedRoute, private abstractsService: AbstractsService) {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.abstractsService.getOneCaseAbstract(Number(this.id)).subscribe(value => {
      this.abstract = value;
    });
  }

  ngOnInit() {
  }

}
