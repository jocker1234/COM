import { Component, OnInit } from '@angular/core';
import {ResearchAbstract} from "../../research-abstract";
import {AbstractsService} from "../../../abstracts.service";
import {Category} from "../../../category";
import {CategoryService} from "../../../category.service";
import {Router} from "@angular/router";
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-research-abstract-create',
  templateUrl: './research-abstract-create.component.html',
  styleUrls: ['./research-abstract-create.component.scss']
})
export class ResearchAbstractCreateComponent implements OnInit {

  categories: Category[];

  abstractForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    authors: new FormArray([
      new FormControl('')
    ]),
    tutors: new FormControl('', [Validators.required]),
    categoryId: new FormControl(''),
    introduction: new FormControl(''),
    aimOfTheStudy: new FormControl(''),
    materialAndMethods: new FormControl(''),
    results: new FormControl(''),
    conclusions: new FormControl('')
  });

  constructor(protected abstractService: AbstractsService, protected categoryService: CategoryService,
              protected router: Router) { }

  ngOnInit(): void {
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
