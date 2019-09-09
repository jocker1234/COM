import {Abstract} from "./abstract";
import {User} from "../user/user";
import {Category} from "../category";

export class CaseAbstract extends Abstract{
  background: string;
  caseReport: string;
  conclusion: string;

  constructor(title: string, authors: string[], tutors: string, status: string,
              users: User, category: Category, background: string, caseReport: string, conclusion: string) {
    super(title, authors, tutors, status, users, category);
    this.background = background;
    this.caseReport = caseReport;
    this.conclusion = conclusion;
  }
}
