import {Abstract} from "./abstract";
import {User} from "../user/user";
import {Category} from "../category";

export class ResearchAbstract extends Abstract{
  introdution: string;
  aimOfTheStudy: string;
  materialAndMethods: string;
  results: string;
  conclusions: string;


  constructor(title: string, authors: string[], tutors: string, status: string, users: User, category: Category,
              introdution: string, aimOfTheStudy: string, materialAndMethods: string, results: string,
              conclusions: string) {

    super(title, authors, tutors, status, users, category);
    this.introdution = introdution;
    this.aimOfTheStudy = aimOfTheStudy;
    this.materialAndMethods = materialAndMethods;
    this.results = results;
    this.conclusions = conclusions;
  }
}
