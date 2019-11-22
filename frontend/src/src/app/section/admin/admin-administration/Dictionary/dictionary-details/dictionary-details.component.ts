import { Component, OnInit } from '@angular/core';
import {Dictionary} from "../../dictionary";
import {ErrorHandler} from "../../../../error-handler";
import {DictionaryService} from "../../../../../service/dictionary.service";
import {ActivatedRoute} from "@angular/router";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";

@Component({
  selector: 'app-dictionary-details',
  templateUrl: './dictionary-details.component.html',
  styleUrls: ['./dictionary-details.component.scss']
})
export class DictionaryDetailsComponent implements OnInit {

  private _dictionary: Dictionary;
  private _error: ErrorHandler;
  private id: number;
  private readonly imageType : string = 'data:image/jpg;base64,';
  private _image: SafeResourceUrl;

  constructor(private dictionaryService: DictionaryService, private activatedRouter: ActivatedRoute, private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.id = +this.activatedRouter.snapshot.paramMap.get('id');
    this.dictionaryService.getOne(this.id).subscribe(value => {
      this._dictionary = value;
      if(value.image!==undefined&&value.image!==null) {
        this._image = this.sanitizer.bypassSecurityTrustResourceUrl(this.imageType + this.dictionary.image);
      }
    });
  }

  checkErrorIsNotUndefined() {
    return this._error !== undefined;
  }

  get image(): SafeResourceUrl {
    return this._image;
  }

  get dictionary(): Dictionary {
    return this._dictionary;
  }

  get error(): ErrorHandler {
    return this._error;
  }


}
