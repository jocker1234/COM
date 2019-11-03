import { Component, OnInit } from '@angular/core';
import {CaseAbstractCreateComponent} from "../case-abstract-create/case-abstract-create.component";
import {AbstractsService} from "../../../../service/abstracts.service";
import {CategoryService} from "../../../../service/category.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Category} from "../../../category";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-case-abstract-edit',
  templateUrl: './case-abstract-edit.component.html',
  styleUrls: ['./case-abstract-edit.component.scss']
})
export class CaseAbstractEditComponent extends CaseAbstractCreateComponent implements OnInit {

  private _id: number;
  private _category: Category;

  constructor(protected abstractService: AbstractsService, protected categoryService: CategoryService,
              protected router: Router, private route: ActivatedRoute, private fb: FormBuilder) {
    super(abstractService, categoryService, router);
  }

  ngOnInit() {
    this.categoryService.getCategory().subscribe(data => this.categories = data);
    this._id = +this.route.snapshot.paramMap.get('_id');
    this.abstractService.getOneCaseAbstract(Number(this._id)).subscribe(value => {
      this.abstractForm.patchValue(value);
      this._category = value.category;
      this.abstractForm.get('categoryId').setValue(this._category.id);
      this.abstractForm.setControl('authors', this.fb.array(value.authors));
    });
  }

  get id(): number {
    return this._id;
  }

  get category(): Category {
    return this._category;
  }
}
