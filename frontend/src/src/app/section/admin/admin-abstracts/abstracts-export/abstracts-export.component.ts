import {Component, OnInit} from '@angular/core';
import {ExportComponent} from "../../../../export/export.component";
import {AbstractsService} from "../../../../service/abstracts.service";
import * as fileSaver from 'file-saver';
import {ErrorHandler} from "../../../error-handler";
import {SuccessfulHandler} from "../../../successful-handler";

@Component({
  selector: 'app-abstracts-export',
  templateUrl: './../../../../export/export.component.html',
  styleUrls: ['./abstracts-export.component.scss']
})
export class AbstractsExportComponent extends ExportComponent implements OnInit {

  constructor(private abstractsService: AbstractsService) {
    super();
  }

  ngOnInit() {
    this.export.type = "Abstracts Export";
    this.export.format = ".docx";
    this.export.fileName = "abstracts";
  }

  onSubmit() {
    this.abstractsService.exports().subscribe(value => {
        const blob = new Blob([value.body], {type: 'application/octet-stream; charset=utf-8'});
        console.log(blob);
        fileSaver.saveAs(blob, this.export.fileName + this.export.format);
        this.successful = new SuccessfulHandler("Successful");
      },
      error1 => {
        this.error = new ErrorHandler(error1._error.message);
        scroll(0, 0);
      }
    );
  }

}
