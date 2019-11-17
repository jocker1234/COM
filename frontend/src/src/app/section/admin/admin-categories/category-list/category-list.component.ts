import { Component, OnInit } from '@angular/core';
import {Category} from "../../../category";
import {CategoryService} from "../../../../service/category.service";
import {FormControl, FormGroup} from "@angular/forms";
import {ErrorHandler} from "../../../error-handler";

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {

  private _categories: Category[];
  private _error: ErrorHandler;
  private _editable: boolean = false;

  categoryForm = new FormGroup({
    id: new FormControl(),
    name: new FormControl('')
  });

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.categoryService.getCategory().subscribe(value => {
      this._categories = value;
    });
  }

  get categories(): Category[] {
    return this._categories;
  }

  get error(): ErrorHandler {
    return this._error;
  }

  get editable(): boolean {
    return this._editable;
  }

  addCategory() {
    this.categoryService.createCategory(this.categoryForm.value).subscribe(
      data => {
        this._categories = data;
        this.categoryForm.reset();
      },error1 => {
        this._error = new ErrorHandler(error1._error.message);
        scroll(0,0);
      }
    );
  }

  prepareToUpdate(category: Category) {
    this.categoryForm.patchValue(category);
    this._editable = true;
  }

  updateCategory() {
    this.categoryService.updateCategory(+this.categoryForm.get('id').value, this.categoryForm.value).subscribe(
      data => {
        this._categories = data;
        this.categoryForm.reset();
        this._editable = false;
      },error1 => {
        this._error = new ErrorHandler(error1._error.message);
        this.categoryForm.reset();
        this._editable = false;
        scroll(0,0);
      }
    );
  }

  deleteCategory() {
    this.categoryService.deleteCategory(+this.categoryForm.get('id').value).subscribe(
      data => {
        this._categories = data;
        this.categoryForm.reset();
        this._editable = false;
      },error1 => {
        this._error = new ErrorHandler(error1._error.message);
        this.categoryForm.reset();
        this._editable = false;
        scroll(0,0);
      }
    );
  }

  cancel() {
    this.categoryForm.reset();
    this._editable = false;
  }
}
