import {Abstract} from "./abstract";
import {User} from "../user/user";
import {Category} from "../category";

export class ResearchAbstract extends Abstract{
  introdution: string;
  aimOfTheStudy: string;
  materialAndMethods: string;
  results: string;
  conclusions: string;

  constructor() {
    super();
  }
}
