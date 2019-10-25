import { Component, OnInit } from '@angular/core';
import {Category} from "../../../category";
import {CategoryService} from "../../../category.service";

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {

  private categories: Category[];

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.categoryService.getCategory().subscribe(value => {
      this.categories = value;
    });
  }

}
