import {Component, ElementRef, OnInit} from '@angular/core';
import {NgbActiveModal, NgbModule} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-choose-new-abstract-content',
  templateUrl: './choose-new-abstract-content.component.html',
  styleUrls: ['./choose-new-abstract-content.component.scss']
})
export class ChooseNewAbstractContentComponent implements OnInit {

  constructor(private dialog: NgbActiveModal) { }

  ngOnInit() {
  }

  closeDialog(){
    this.dialog.close({event:'close',data:this});
  }

}
