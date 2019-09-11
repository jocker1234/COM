import { Component, OnInit } from '@angular/core';
import {CaseAbstract} from "../../case-abstract";
import {Category} from "../../../category";
import {CategoryService} from "../../../category.service";
import {AbstractsService} from "../../../abstracts.service";
import {Authors} from "../../../authors";

@Component({
  selector: 'app-case-abstract-create',
  templateUrl: './case-abstract-create.component.html',
  styleUrls: ['./case-abstract-create.component.scss']
})
export class CaseAbstractCreateComponent implements OnInit {

  author: any;
  categories: Category[];
  case: CaseAbstract = new CaseAbstract();

  constructor(private abstractService: AbstractsService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.case.authors = [];
    this.categoryService.getCategory().subscribe(data => this.categories = data);
  }

  onSubmit() {
    this.case.authors.push(this.author);
    this.abstractService.newCaseAbstract(this.case).subscribe(value => console.log("as " + value ));
  }

}
