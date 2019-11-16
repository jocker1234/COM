import {Component, Input, OnInit} from '@angular/core';
import {Abstract} from "./abstract";
import {AbstractsService} from "../../service/abstracts.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ChooseNewAbstractContentComponent} from "./choose-new-abstract-content/choose-new-abstract-content.component";
import {CategoryService} from "../../service/category.service";
import {ErrorHandler} from "../error-handler";

@Component({
  selector: 'app-abstracts',
  templateUrl: './abstracts.component.html',
  styleUrls: ['./abstracts.component.css']
})
export class AbstractsComponent implements OnInit {

  private _abstractList: Abstract[];

  constructor(private abstractService: AbstractsService, private router: Router, private dialog: NgbModal,
              private categoryService: CategoryService) {
    console.log(history.state);
  }

  ngOnInit() {
    this.abstractService.getAllUserAbstracts().subscribe(value => this._abstractList = value);
    if (this._abstractList !== undefined) {
      this._abstractList.forEach(abstract => {
        this.categoryService.getCategoryById(abstract.categoryId).subscribe(category => abstract.category = category);
      });
    }
  }

  get abstractList(): Abstract[] {
    return this._abstractList;
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ChooseNewAbstractContentComponent);
  }

}
