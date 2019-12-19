import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ResearchAbstract} from "../../research-abstract";
import {AbstractsService} from "../../../../service/abstracts.service";
import {Category} from "../../../category";
import {CategoryService} from "../../../../service/category.service";
import {Router} from "@angular/router";
import {FormArray, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";

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
      new FormControl('', [Validators.required])
    ]),
    tutors: new FormControl('', [Validators.required]),
    affiliation: new FormControl('', [Validators.required]),
    categoryId: new FormControl('', [Validators.required]),
    introduction: new FormControl('', [Validators.required]),
    aimOfTheStudy: new FormControl('', [Validators.required]),
    materialAndMethods: new FormControl('', [Validators.required]),
    results: new FormControl('', [Validators.required]),
    conclusions: new FormControl('', [Validators.required])
  });

  constructor(protected abstractService: AbstractsService, protected categoryService: CategoryService,
              protected router: Router) {
    this.abstractForm.setValidators(this.lengthValidator());
  }

  private lengthValidator(){
    return (group: FormGroup): ValidationErrors => {
      const introduction = group.controls['introduction'];
      const aimOfTheStudy = group.controls['aimOfTheStudy'];
      const materialAndMethods = group.controls['materialAndMethods'];
      const results = group.controls['results'];
      const conclusions = group.controls['conclusions'];
      let lengthFields = introduction.value.length + aimOfTheStudy.value.length + materialAndMethods.value.length
        + results.value.length + conclusions.value.length;
      if(lengthFields > 2300){
        introduction.setErrors({notEquivalent: true});
        aimOfTheStudy.setErrors({notEquivalent: true});
        materialAndMethods.setErrors({notEquivalent: true});
        results.setErrors({notEquivalent: true});
        conclusions.setErrors({notEquivalent: true});
      } else {
        introduction.setErrors(null);
        aimOfTheStudy.setErrors(null);
        materialAndMethods.setErrors(null);
        results.setErrors(null);
        conclusions.setErrors(null);
      }
      return;
    };
  }

  handleException(field: string) {
    if(!(this.abstractForm.get(field).untouched || this.abstractForm.get(field).valid)){
      if(this.abstractForm.get(field).errors != null && this.abstractForm.get(field).errors.notEquivalent != null) {
        return 1;
      }
      return 0;
    }
  }

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

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({onlySelf: true});
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }

  insertable() {
    if(this.abstractForm.valid) {
      return true;
    } else {
      return false;
    }
  }

}
