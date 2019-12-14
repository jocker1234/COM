import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-field-successfull-display',
  templateUrl: './field-successfull-display.component.html',
  styleUrls: ['./field-successfull-display.component.scss']
})
export class FieldSuccessfullDisplayComponent {

  @Input() successfulMsg: string;
  @Input() displaySuccessful: boolean;

}
