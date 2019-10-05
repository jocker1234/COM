import {AuthoritiesResponse} from "../auth/authorities-response";

export class User {
  id: number;
  activatedAccount: boolean;
  country: string;
  dateOfBirth: Date;
  email: string;
  faculty: string;
  firstName: string;
  gender: string;
  lastName: string;
  needVisa: string;
  passportNumber: string;
  password: string;
  phoneNumber: string;
  registrationDate: Date;
  title: string;
  university: string;
  yearOfStudy: number;
  authoritiesSet: AuthoritiesResponse[];

  constructor(user?: User) {
    if (user !== undefined) {
      this.id = user.id;
      this.activatedAccount = user.activatedAccount;
      this.country = user.country;
      this.dateOfBirth = user.dateOfBirth;
      this.email = user.email;
      this.faculty = user.faculty;
      this.firstName = user.firstName;
      this.gender = user.gender;
      this.lastName = user.lastName;
      this.needVisa = user.needVisa;
      this.passportNumber = user.passportNumber;
      this.password = user.password;
      this.phoneNumber = user.phoneNumber;
      this.registrationDate = user.registrationDate;
      this.title = user.title;
      this.university = user.university;
      this.yearOfStudy = user.yearOfStudy;
      this.authoritiesSet = user.authoritiesSet;
    }
  }
}
