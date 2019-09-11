import {Abstract} from "./abstract";
import {User} from "../user/user";
import {Category} from "../category";

export class CaseAbstract extends Abstract{
  background: string;
  caseReport: string;
  conclusions: string;

  constructor() {
    super();
  }

}
