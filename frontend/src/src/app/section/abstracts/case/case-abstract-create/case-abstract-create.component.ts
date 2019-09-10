import { Component, OnInit } from '@angular/core';
import {CaseAbstract} from "../../case-abstract";
import {Category} from "../../../category";
import {CategoryService} from "../../../category.service";
import {AbstractsService} from "../../../abstracts.service";

@Component({
  selector: 'app-case-abstract-create',
  templateUrl: './case-abstract-create.component.html',
  styleUrls: ['./case-abstract-create.component.scss']
})
export class CaseAbstractCreateComponent implements OnInit {

  categories: Category[];
  case: CaseAbstract = new CaseAbstract();

  constructor(private abstractService: AbstractsService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.categoryService.getCategory().subscribe(data => this.categories = data);
  }

  onSubmit() {
    this.abstractService.newCaseAbstract(this.case).subscribe(value => console.log("as " + value ));
  }

}
