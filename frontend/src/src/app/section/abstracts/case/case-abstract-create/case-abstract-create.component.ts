import {Component, OnInit} from '@angular/core';
import {Category} from "../../../category";
import {CategoryService} from "../../../../service/category.service";
import {AbstractsService} from "../../../../service/abstracts.service";
import {Router} from "@angular/router";
import {FormArray, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";

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
      new FormControl('', [Validators.required])
    ]),
    tutors: new FormControl('', [Validators.required]),
    affiliation: new FormControl('', [Validators.required]),
    categoryId: new FormControl('', [Validators.required]),
    background: new FormControl('', [Validators.required]),
    caseReport: new FormControl('', [Validators.required]),
    conclusions: new FormControl('', [Validators.required])
  });

  constructor(protected abstractService: AbstractsService, protected categoryService: CategoryService, protected router: Router) {
    this.abstractForm.setValidators(this.lengthValidator());
  }

  private lengthValidator(){
    return (group: FormGroup): ValidationErrors => {
      const background = group.controls['background'];
      const caseReport = group.controls['caseReport'];
      const conclusions = group.controls['conclusions'];
      let lengthFields = background.value.length + caseReport.value.length + conclusions.value.length;
      if(lengthFields > 2300){
        background.setErrors({notEquivalent: true});
        caseReport.setErrors({notEquivalent: true});
        conclusions.setErrors({notEquivalent: true});
      } else {
        background.setErrors(null);
        caseReport.setErrors(null);
        conclusions.setErrors(null);
      }
      return;
    };
  }

  private handleException(field: string) {
    if(!(this.abstractForm.get(field).untouched || this.abstractForm.get(field).valid)){
      if(this.abstractForm.get(field).errors != null && this.abstractForm.get(field).errors.notEquivalent != null) {
        console.log(1);
        return 1;
      } else {
        console.log(0);
        return 0;
      }
    }
  }

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
