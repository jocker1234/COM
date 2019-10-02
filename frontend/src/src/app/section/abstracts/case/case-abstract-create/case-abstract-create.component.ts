import { Component, OnInit } from '@angular/core';
import {CaseAbstract} from "../../case-abstract";
import {Category} from "../../../category";
import {CategoryService} from "../../../category.service";
import {AbstractsService} from "../../../abstracts.service";
import {Router} from "@angular/router";
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-case-abstract-create',
  templateUrl: './case-abstract-create.component.html',
  styleUrls: ['./case-abstract-create.component.scss']
})
export class CaseAbstractCreateComponent implements OnInit {

  categories: Category[];

  abstractForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    authors: new FormArray([
      new FormControl('')
    ]),
    tutors: new FormControl('', [Validators.required]),
    categoryId: new FormControl(''),
    background: new FormControl(''),
    caseReport: new FormControl(''),
    conclusions: new FormControl('')
  });

  constructor(protected abstractService: AbstractsService, protected categoryService: CategoryService, protected router: Router) { }

  ngOnInit() {
    this.categoryService.getCategory().subscribe(data => this.categories = data);
  }

  get authors(): FormArray {
    return this.abstractForm.get('authors') as FormArray;
  }

  addAuthorField() {
    this.authors.push(new FormControl());
  }

  deleteAuthorField(index: number) {
    this.authors.removeAt(index);
  }

}
