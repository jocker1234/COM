import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ResearchAbstractCreateComponent} from "../research-abstract-create/research-abstract-create.component";
import {AbstractsService} from "../../../abstracts.service";
import {CategoryService} from "../../../category.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Category} from "../../../category";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-research-abstract-edit',
  templateUrl: './research-abstract-edit.component.html',
  styleUrls: ['./research-abstract-edit.component.scss']
})
export class ResearchAbstractEditComponent extends ResearchAbstractCreateComponent implements OnInit {

  private id: number;
  private category: Category;

  constructor(protected abstractService: AbstractsService, protected categoryService: CategoryService,
              protected router: Router, private route: ActivatedRoute, private fb: FormBuilder) {
    super(abstractService, categoryService, router);
  }

  ngOnInit() {
    this.categoryService.getCategory().subscribe(data => this.categories = data);
    this.id = +this.route.snapshot.paramMap.get('id');
    this.abstractService.getOneResearchAbstract(Number(this.id)).subscribe(value => {
      this.abstractForm.patchValue(value);
      this.category = value.category;
      this.abstractForm.get('categoryId').setValue(this.category.id);
      this.abstractForm.setControl('authors', this.fb.array(value.authors));
    });
  }

}
