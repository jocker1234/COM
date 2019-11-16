import {Abstract} from "./abstract";

export class CaseAbstract extends Abstract{
  background: string = '';
  caseReport: string = '';
  conclusions: string = '';

  constructor() {
    super();
    //this.type = 'C';
  }

}
