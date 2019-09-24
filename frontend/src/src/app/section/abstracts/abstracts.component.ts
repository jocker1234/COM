import { Component, OnInit } from '@angular/core';
import {Abstract} from "./abstract";
import {AbstractsService} from "../abstracts.service";

@Component({
  selector: 'app-abstracts',
  templateUrl: './abstracts.component.html',
  styleUrls: ['./abstracts.component.css']
})
export class AbstractsComponent implements OnInit {

  private abstractList: Abstract[];

  constructor(private abstractService: AbstractsService) { }

  ngOnInit() {
    //this.abstractList = this.abstractService.getAllUserAbstracts();
  }

}
