import {Category} from "../category";

export class Abstract {
  id: number;
  title: string = '';
  authors: any[] = [];
  tutors: string = '';
  categoryId: number;
  category: Category = new Category();
  type: string = '';
  status: string = '';

  constructor() {
  }
}
