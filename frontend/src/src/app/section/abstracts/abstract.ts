import {Category} from "../category";

export class Abstract {
  id: number;
  title: string = '';
  authors: any[] = [];
  tutors: string = '';
  categoryId: number;
  category: Category;
  type: string = '';

  constructor() {
  }
}
