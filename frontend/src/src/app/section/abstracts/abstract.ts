import {User} from "../user/user";
import {Category} from "../category";
import {Authors} from "../authors";

export class Abstract {
  id: number;
  title: string;
  authors: any[];
  tutors: string;
  users: User;
  categoryId: number;

  constructor() {
  }
}
