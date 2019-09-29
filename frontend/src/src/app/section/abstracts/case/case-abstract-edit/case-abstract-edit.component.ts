import { Component, OnInit } from '@angular/core';
import {CaseAbstractCreateComponent} from "../case-abstract-create/case-abstract-create.component";
import {AbstractsService} from "../../../abstracts.service";
import {CategoryService} from "../../../category.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-case-abstract-edit',
  templateUrl: './case-abstract-edit.component.html',
  styleUrls: ['./case-abstract-edit.component.scss']
})
export class CaseAbstractEditComponent extends CaseAbstractCreateComponent implements OnInit {

  private id: number;

  constructor(protected abstractService: AbstractsService, protected categoryService: CategoryService,
              protected router: Router, private route: ActivatedRoute) {
    super(abstractService, categoryService, router);
  }

  ngOnInit() {
    this.categoryService.getCategory().subscribe(data => this.categories = data);
    this.id = +this.route.snapshot.paramMap.get('id');
    this.abstractService.getOneCaseAbstract(Number(this.id)).subscribe(value => {
      this.abstractForm.patchValue(value);
    });
  }

}
