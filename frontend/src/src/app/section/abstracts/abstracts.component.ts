import {Component, OnInit} from '@angular/core';
import {Abstract} from "./abstract";
import {AbstractsService} from "../abstracts.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ChooseNewAbstractContentComponent} from "./choose-new-abstract-content/choose-new-abstract-content.component";
import {CategoryService} from "../category.service";

@Component({
  selector: 'app-abstracts',
  templateUrl: './abstracts.component.html',
  styleUrls: ['./abstracts.component.css']
})
export class AbstractsComponent implements OnInit {

  protected abstractList: Abstract[];

  constructor(private abstractService: AbstractsService, private router: Router, private dialog: NgbModal,
              private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.abstractService.getAllUserAbstracts().subscribe(value => this.abstractList = value);
    if (this.abstractList !== undefined) {
      this.abstractList.forEach(abstract => {
        this.categoryService.getCategoryById(abstract.categoryId).subscribe(category => abstract.category = category);
      });
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ChooseNewAbstractContentComponent);

  }

}
