import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, NavigationEnd, PRIMARY_OUTLET, Router} from "@angular/router";
import {distinctUntilChanged, filter, map} from "rxjs/operators";
import {BreadCrumb} from "./bread-crumb";

@Component({
  selector: 'app-bread-crumb',
  templateUrl: './bread-crumb.component.html',
  styleUrls: ['./bread-crumb.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class BreadCrumbComponent implements OnInit {
  breadcrumbs$ = this.router.events.pipe(
    filter(event => event instanceof NavigationEnd),
    distinctUntilChanged(),
    map(event => this.getBreadcrumbs(this.activatedRoute.root))
  );

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
  }


  getBreadcrumbs(route: ActivatedRoute, url: string = '',
                 breadcrumbs: Array<BreadCrumb> = []): Array<BreadCrumb> {
    const ROUTE_DATA_BREADCRUMB: string = "breadcrumb";

    if (breadcrumbs.length === 0) {
      let  breadcrumb = {
        label: "14TH BIALYSTOK INTERNATIONAL MEDICAL CONGRESS",
        params: [],
        url: "/home"
      };
      breadcrumbs.push(breadcrumb);
    }
    console.log(breadcrumbs)
    //get the child routes
    let children: ActivatedRoute[] = route.children;

    //return if there are no more children
    if (children.length === 0) {
      return breadcrumbs;
    }

    //iterate over each children
    for (let child of children) {
      //verify primary route
      if (child.outlet !== PRIMARY_OUTLET) {
        continue;
      }

      //verify the custom data property "breadcrumb" is specified on the route
      if (!child.snapshot.data.hasOwnProperty(ROUTE_DATA_BREADCRUMB)) {
        return this.getBreadcrumbs(child, url, breadcrumbs);
      }

      //get the route's URL segment
      let routeURL: string = child.snapshot.url.map(segment => segment.path).join("/");

      //append route URL to URL
      url += `/${routeURL}`;

      //add breadcrumb
      let breadcrumb: BreadCrumb = {
        label: child.snapshot.data[ROUTE_DATA_BREADCRUMB],
        params: child.snapshot.params,
        url: url
      };
      breadcrumbs.push(breadcrumb);

      //recursive
      return this.getBreadcrumbs(child, url, breadcrumbs);
    }
  }

}
