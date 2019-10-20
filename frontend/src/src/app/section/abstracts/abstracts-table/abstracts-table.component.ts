import {Component, Input, OnInit} from '@angular/core';
import {Abstract} from "../abstract";

@Component({
  selector: 'app-abstracts-table',
  templateUrl: './abstracts-table.component.html',
  styleUrls: ['./abstracts-table.component.scss']
})
export class AbstractsTableComponent implements OnInit {

  @Input()
  protected abstractList: Abstract[];

  constructor() { }

  ngOnInit() {
  }

  getRouterLink(abstract: Abstract) {
    let url = "";
    if (abstract.type == 'c' || abstract.type == 'C') {
      url += "case/";
    } else if (abstract.type == 'r' || abstract.type == 'R') {
      url += "research/";
    }
    url += abstract.id;
    return url;
  }

}