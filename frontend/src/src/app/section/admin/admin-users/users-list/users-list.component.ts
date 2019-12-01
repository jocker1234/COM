import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {UserService} from "../../../../service/user.service";
import {User} from "../../../user/user";
import {compare, SortableHeaderDirective} from "../../../../sortable/sortable-header.directive";
import {SortEvent} from "../../../../sortable/sort-event";
import {faAngleDown, faAngleUp} from "@fortawesome/free-solid-svg-icons";
import {AuthService} from "../../../../service/auth.service";
import {CategoryService} from "../../../../service/category.service";
import {Category} from "../../../category";
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;
  private _users: User[];
  private usersCopy: User[];
  private _keySort = undefined;
  private _reverseSort = undefined;
  faAngleDown = faAngleDown;
  faAngleUp = faAngleUp;
  private _countries: {};
  private _title: string[] = ['Student', 'PhD Student'];
  private _yearOfStudy: string[] = ['1', '2', '3', '4', '5', '6'];
  private _status: string[] = ['Send', 'Approved', 'Rejected'];
  private _type: string[] = ['Case_Report', 'Research'];
  private _categories: Category[];

  searchCriteria = new FormGroup({
    country: new FormControl(''),
    university: new FormControl(''),
    title: new FormControl(''),
    yearOfStudy: new FormControl('0'),
    status: new FormControl(''),
    type: new FormControl(''),
    category: new FormControl('')
  });

  private searchCriteriaReset = new FormGroup({
    country: new FormControl(''),
    university: new FormControl(''),
    title: new FormControl(''),
    yearOfStudy: new FormControl('0'),
    status: new FormControl(''),
    type: new FormControl(''),
    category: new FormControl('')
  });

  constructor(private categoryService: CategoryService, private authService: AuthService,
              protected userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUsers(this.searchCriteria.value).subscribe(usersData => {
      this._users = usersData;
      this.usersCopy = usersData;
    });
    this.authService.countryList().subscribe(data => this._countries = data);
    this.categoryService.getCategory().subscribe(value => {
      this._categories = value;
    });
  }

  get users(): User[] {
    return this._users;
  }

  get countries(): {} {
    return this._countries;
  }

  get yearOfStudy(): string[] {
    return this._yearOfStudy;
  }

  get status(): string[] {
    return this._status;
  }

  get type(): string[] {
    return this._type;
  }

  get categories(): Category[] {
    return this._categories;
  }

  get title(): string[] {
    return this._title;
  }

  get keySort(): any {
    return this._keySort;
  }

  get reverseSort(): any {
    return this._reverseSort;
  }


  onSortedUser({column, direction}: SortEvent) {
    this._keySort = column;
    this._reverseSort = direction === '' ? undefined : direction === 'asc' ? true : false;
    this.headers.forEach(header => {
      if(header.sortable != column) {
        header.direction = '';
      }
    });
    if(direction === '') {
      this._users = this.usersCopy;
    } else {
      this._users = [...this.usersCopy].sort((a,b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  clearForm() {
    this.searchCriteria.reset(this.searchCriteriaReset.value);
  }

  find() {
    console.log(this.searchCriteria.value)
    this.userService.getUsers(this.searchCriteria.value).subscribe(usersData => {
      this._users = usersData;
    });
  }
}
