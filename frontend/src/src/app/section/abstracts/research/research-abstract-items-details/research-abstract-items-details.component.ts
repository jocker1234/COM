import {Component, OnInit} from "@angular/core";
import {ResearchAbstract} from "../../research-abstract";
import {ActivatedRoute} from "@angular/router";
import {AbstractsService} from "../../../../service/abstracts.service";
import {ErrorHandler} from "../../../error-handler";

@Component({
  selector: 'app-research-abstract-items-details',
  templateUrl: './research-abstract-items-details.component.html',
  styleUrls: ['./research-abstract-items-details.component.scss']
})
export class ResearchAbstractItemsDetailsComponent implements OnInit {

  protected _abstract: ResearchAbstract = new ResearchAbstract();
  protected _id: number;
  protected _error: ErrorHandler;

  constructor(private route: ActivatedRoute, private abstractsService: AbstractsService) {
    this._id= +this.route.snapshot.paramMap.get('id');
    this.abstractsService.getOneResearchAbstract(Number(this._id)).subscribe(value => {
      this._abstract = value;
    }, error1 => {
      console.log(error1.error.message)
      this._error = new ErrorHandler(error1.error.message);
      scroll(0,0)
    });
  }

  ngOnInit() {
  }

  get abstract(): ResearchAbstract {
    return this._abstract;
  }

  get id(): number {
    return this._id;
  }

  get error(): ErrorHandler {
    return this._error;
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

}
