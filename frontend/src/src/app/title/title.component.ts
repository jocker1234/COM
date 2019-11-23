import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {distinctUntilChanged, filter, map} from "rxjs/operators";
import {ActivatedRoute, NavigationEnd, PRIMARY_OUTLET, Router} from "@angular/router";
import {BreadCrumb} from "../bread-crumb/bread-crumb";
import {Title} from "./title";

@Component({
  selector: 'app-title',
  templateUrl: './title.component.html',
  styleUrls: ['./title.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class TitleComponent implements OnInit {
  title$ = this.router.events.pipe(
    filter(event => event instanceof NavigationEnd),
    distinctUntilChanged(),
    map(event => this.getTitle(this.activatedRoute.root))
  );

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
  }


  getTitle(route: ActivatedRoute, url: string = '',
                 title: Array<Title> = []): Array<Title> {
    const ROUTE_DATA_TITLE: string = "title";

    if (title.length === 0) {
      let  title1 = {
        title: "14TH BIALYSTOK INTERNATIONAL MEDICAL CONGRESS"
      };
      title.push(title1);
    }
    //get the child routes
    let children: ActivatedRoute[] = route.children;

    //return if there are no more children
    if (children.length === 0) {
      return title;
    }

    //iterate over each children
    for (let child of children) {
      //verify primary route
      if (child.outlet !== PRIMARY_OUTLET) {
        continue;
      }

      //verify the custom data property "breadcrumb" is specified on the route
      if (!child.snapshot.data.hasOwnProperty(ROUTE_DATA_TITLE)) {
        return this.getTitle(child, url, title);
      }

      //get the route's URL segment
      let routeURL: string = child.snapshot.url.map(segment => segment.path).join("/");

      //add breadcrumb
      let title1: Title = {
        title: child.snapshot.data[ROUTE_DATA_TITLE]
      };
      title.push(title1);

      //recursive
      return this.getTitle(child, url, title);
    }
  }

}
