import { Component, OnInit } from '@angular/core';
import {ResearchAbstract} from "../../research-abstract";
import {AbstractsService} from "../../../abstracts.service";
import {Category} from "../../../category";
import {CategoryService} from "../../../category.service";

@Component({
  selector: 'app-research-abstract-create',
  templateUrl: './research-abstract-create.component.html',
  styleUrls: ['./research-abstract-create.component.scss']
})
export class ResearchAbstractCreateComponent implements OnInit {

  author: any;
  categories: Category[];
  research: ResearchAbstract = new ResearchAbstract();

  constructor(private abstractService: AbstractsService, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.research.authors = [];
    this.categoryService.getCategory().subscribe(data => this.categories = data);
  }

  onSubmit() {
    this.research.authors.push(this.author);
    this.abstractService.newResearchAbstract(this.research).subscribe(value => console.log("as " + value ));
  }

}
