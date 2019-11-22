import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {DictionaryService} from "./dictionary.service";

const LOCATION_CONFERENCE = 'conference_time';
const TIME_CONFERENCE = 'conference_place';

@Injectable({
  providedIn: 'root'
})
export class DictionaryStorageService {

  constructor(private router: Router, private dictionaryService: DictionaryService) { }

  public saveLocation(location: string) {
    window.sessionStorage.removeItem(LOCATION_CONFERENCE);
    window.sessionStorage.setItem(LOCATION_CONFERENCE, location);
  }

  public getLocation(): string {
    if(window.sessionStorage.getItem(LOCATION_CONFERENCE)===null || window.sessionStorage.getItem(LOCATION_CONFERENCE) === undefined) {
      this.dictionaryService.findByKey(LOCATION_CONFERENCE).subscribe(value => {
        console.log(value);
        this.saveLocation(value)
      });
    }
    return sessionStorage.getItem(LOCATION_CONFERENCE);
  }

  public saveTime(time: string) {
    window.sessionStorage.removeItem(TIME_CONFERENCE);
    window.sessionStorage.setItem(TIME_CONFERENCE, time);
  }

  public getTime(): string {
    if(window.sessionStorage.getItem(TIME_CONFERENCE)===null || window.sessionStorage.getItem(TIME_CONFERENCE) === undefined) {
      this.dictionaryService.findByKey(TIME_CONFERENCE).subscribe(value => {
        console.log(value);
        this.saveTime(value)
      },error1 => {
        console.log(error1)
      });
    }
    return sessionStorage.getItem(TIME_CONFERENCE);
  }
}
