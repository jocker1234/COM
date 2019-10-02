import {Abstract} from "./abstract";

export class ResearchAbstract extends Abstract{
  introduction: string = '';
  aimOfTheStudy: string = '';
  materialAndMethods: string = '';
  results: string = '';
  conclusions: string = '';

  constructor() {
    super();
    this.type = 'R';
  }
}
