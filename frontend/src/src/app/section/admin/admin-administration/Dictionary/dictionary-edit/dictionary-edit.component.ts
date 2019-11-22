import { Component, OnInit } from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {DictionaryService} from "../../../../../service/dictionary.service";
import {ErrorHandler} from "../../../../error-handler";
import {Dictionary} from "../../dictionary";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-dictionary-edit',
  templateUrl: './dictionary-edit.component.html',
  styleUrls: ['./dictionary-edit.component.scss']
})
export class DictionaryEditComponent implements OnInit {
  private id: number;
  private _error: ErrorHandler;

  dictionaryForm = new FormGroup({
    addDate: new FormControl(''),
    editDate: new FormControl(''),
    key: new FormControl(''),
    value: new FormControl(''),
    image: new FormControl('')
  });

  constructor(protected dictionaryService: DictionaryService, protected router: Router, private activatedRouter: ActivatedRoute,
              private httpClient: HttpClient) { }

  ngOnInit() {
    this.id = +this.activatedRouter.snapshot.paramMap.get('id');
    this.dictionaryService.getOne(this.id).subscribe(data =>
      this.dictionaryForm.patchValue(data)
    );
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

  get error(): ErrorHandler {
    return this._error;
  }

  selectedFile: File;
  imgUrl: any;
  onFileChanged(event) {
    console.log(event.target.files[0]);
    this.selectedFile = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgUrl = reader.result;
    }
  }

  onSubmit() {
    if(this.selectedFile!==null&&this.selectedFile!==undefined){
      let uploadData = new FormData();
      uploadData.append("myFile", this.selectedFile);
      this.dictionaryService.updateImage(this.id, uploadData).subscribe(
        data => {
          this.router.navigate(['admin/administration/dictionary/' + this.id]);
        }, error1 => {
          this._error = new ErrorHandler(error1.error.message);
          scroll(0, 0)
        }
      )
    } else {
      this.dictionaryService.update(this.id, this.dictionaryForm.value).subscribe(
        data => {
          this.router.navigate(['admin/administration/dictionary/' + this.id]);
        }, error1 => {
          this._error = new ErrorHandler(error1.error.message);
          scroll(0, 0)
        }
      )
    }
  }
}
