import {Component, OnInit} from '@angular/core';
import {ExportComponent} from "../../../../export/export.component";
import {UserService} from "../../../../service/user.service";
import * as fileSaver from 'file-saver';
import {ErrorHandler} from "../../../error-handler";
import {SuccessfulHandler} from "../../../successful-handler";

@Component({
  selector: 'app-users-export',
  templateUrl: './../../../../export/export.component.html',
  styleUrls: ['./users-export.component.scss']
})
export class UsersExportComponent extends ExportComponent implements OnInit {

  constructor(private userService: UserService) {
    super();
  }

  ngOnInit() {
    this.export.type = "User Export";
    this.export.format = ".csv";
    this.export.fileName = "users";
  }

  onSubmit() {
    this.userService.exports().subscribe(value => {
        const blob = new Blob([value.body], {type: 'text/csv; charset=utf-8'});
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
