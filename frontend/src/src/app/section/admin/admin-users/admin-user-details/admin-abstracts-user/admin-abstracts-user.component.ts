import {Component, Input, OnInit} from '@angular/core';
import {UsersListComponent} from "../../users-list/users-list.component";
import {UserService} from "../../../../../service/user.service";
import {AbstractsService} from "../../../../../service/abstracts.service";
import {AbstractsComponent} from "../../../../abstracts/abstracts.component";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CategoryService} from "../../../../../service/category.service";
import {Abstract} from "../../../../abstracts/abstract";
import {AbstractsTableComponent} from "../../../../abstracts/abstracts-table/abstracts-table.component";

@Component({
  selector: 'app-admin-abstracts-user',
  templateUrl: './../../../../abstracts/abstracts-table/abstracts-table.component.html',
  styleUrls: ['./admin-abstracts-user.component.scss']
})
export class AdminAbstractsUserComponent extends AbstractsTableComponent implements OnInit {

  constructor() {
    super();
  }

  ngOnInit() {
  }

  getRouterLink(abstract: Abstract) {
    let url = "../../abstract/";
    if (abstract.type == 'c' || abstract.type == 'C'|| abstract.type == 'Case') {
      url += "case/";
    } else if (abstract.type == 'r' || abstract.type == 'R'|| abstract.type == 'Research') {
      url += "research/";
    }
    url += abstract.id;
    return url;
  }

}
