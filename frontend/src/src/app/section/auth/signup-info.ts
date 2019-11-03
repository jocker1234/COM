export class SignUpInfo {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  //gender: string;
  dateOfBirth: Date;
  country: string;
  title: string;
  university: string;
  faculty: string;
  yearOfStudy: number;
  phoneNumber: string;
  needVisa: boolean;
  passportNumber: string;
  authorities: string[];


  constructor(email, password, firstName, lastName, /*gender,*/
              dateOfBirth, country, title, university, faculty, yearOfStudy,
              phoneNumber, needVisa, passportNumber, authorities) {

    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    //this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.country = country;
    this.title = title;
    this.university = university;
    this.faculty = faculty;
    this.yearOfStudy = yearOfStudy;
    this.phoneNumber = phoneNumber;
    this.needVisa = needVisa;
    this.passportNumber = passportNumber;
    this.authorities = authorities;
  }
}
