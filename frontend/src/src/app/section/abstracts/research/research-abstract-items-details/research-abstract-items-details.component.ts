import {Component, OnInit} from "@angular/core";
import {ResearchAbstract} from "../../research-abstract";
import {ActivatedRoute} from "@angular/router";
import {AbstractsService} from "../../../abstracts.service";

@Component({
  selector: 'app-research-abstract-items-details',
  templateUrl: './research-abstract-items-details.component.html',
  styleUrls: ['./research-abstract-items-details.component.scss']
})
export class ResearchAbstractItemsDetailsComponent implements OnInit {

  abstract: ResearchAbstract = new ResearchAbstract();
  id: number;

  constructor(private route: ActivatedRoute, private abstractsService: AbstractsService) {
    this.id= +this.route.snapshot.paramMap.get('id');
    this.abstractsService.getOneResearchAbstract(Number(this.id)).subscribe(value => {
      this.abstract = value;
    });
  }

  ngOnInit() {
  }

}
