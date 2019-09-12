import {User} from "../user/user";

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
