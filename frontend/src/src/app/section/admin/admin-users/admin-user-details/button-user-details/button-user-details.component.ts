import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-button-user-details',
  templateUrl: './button-user-details.component.html',
  styleUrls: ['./button-user-details.component.scss']
})
export class ButtonUserDetailsComponent implements OnInit {

  @Input()
  private id: number;

  constructor() { }

  ngOnInit() {
  }

}
