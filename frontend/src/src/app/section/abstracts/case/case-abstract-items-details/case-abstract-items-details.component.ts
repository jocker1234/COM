import {Component, OnInit} from "@angular/core";
import {CaseAbstract} from "../../case-abstract";
import {ActivatedRoute, Router} from "@angular/router";
import {AbstractsService} from "../../../../service/abstracts.service";
import {ErrorHandler} from "../../../error-handler";

@Component({
  selector: 'app-case-abstract-items-details',
  templateUrl: './case-abstract-items-details.component.html',
  styleUrls: ['./case-abstract-items-details.component.scss']
})
export class CaseAbstractItemsDetailsComponent implements OnInit {

  protected _abstract: CaseAbstract = new CaseAbstract();
  protected _id: number;
  protected _error: ErrorHandler;

  constructor(private route: ActivatedRoute, private abstractsService: AbstractsService) {
    this._id = +this.route.snapshot.paramMap.get('id');
    this.abstractsService.getOneCaseAbstract(Number(this._id)).subscribe(value => {
      this._abstract = value;
    }, error1 => {
      this._error = new ErrorHandler(error1.error.message);
      scroll(0,0)
    });
  }

  get error(): ErrorHandler {
    return this._error;
  }

  get abstract(): CaseAbstract {
    return this._abstract;
  }

  get id(): number {
    return this._id;
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

  ngOnInit() {
  }

}
