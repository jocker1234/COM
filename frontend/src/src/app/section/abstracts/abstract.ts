import {User} from "../user/user";
import {Category} from "../category";

export class Abstract {
  id: number;
  title: string;
  authors: string[];
  tutors: string;
  status: string;
  users: User;
  category: Category;

  constructor(title: string, authors: string[], tutors: string, status: string, users: User, category: Category) {
    this.title = title;
    this.authors = authors;
    this.tutors = tutors;
    this.status = status;
    this.users = users;
    this.category = category;
  }
}
