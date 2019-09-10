import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ResearchAbstract} from "../../research-abstract";
import {AbstractsService} from "../../../abstracts.service";

@Component({
  selector: 'app-research-abstract-create',
  templateUrl: './research-abstract-create.component.html',
  styleUrls: ['./research-abstract-create.component.scss']
})
export class ResearchAbstractCreateComponent implements OnInit {

  modelForm: FormGroup;

  research: ResearchAbstract;
  researchAbstractError: ResearchAbstract;

  private validationMessages = {
    title: {
      required: 'firstname is required'
    },
    authors: {
      required: 'lastname is required',
      minlength: 'lastname must have at least 3 characters'
    }
  }

  constructor(private formBuilder: FormBuilder, private abstractService: AbstractsService) { }

  ngOnInit(): void {
    this.modelForm = this.formBuilder.group({
      title: ['', Validators.required],
      authors: [[]],
      tutors: ['', Validators.required],
      category: [[]],
      introdution: ['', Validators.required],
      aimOfTheStudy: ['', Validators.required],
      materialAndMethods: ['', Validators.required],
      results: ['', Validators.required],
      conclusions: ['', Validators.required]
    });
  }

  get f() { return this.modelForm.controls; }

  onSubmit() {
    console.log(this.modelForm.value);
    this.abstractService.newResearchAbstract(this.modelForm.value);
  }

}
