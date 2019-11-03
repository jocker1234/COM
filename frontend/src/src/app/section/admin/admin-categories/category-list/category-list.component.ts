import { Component, OnInit } from '@angular/core';
import {Category} from "../../../category";
import {CategoryService} from "../../../../service/category.service";

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {

  private _categories: Category[];

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.categoryService.getCategory().subscribe(value => {
      this._categories = value;
    });
  }

  get categories(): Category[] {
    return this._categories;
  }
}
