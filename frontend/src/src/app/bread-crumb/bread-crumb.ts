import {Params} from "@angular/router";

export interface BreadCrumb {
  label: string;
  params?: Params;
  url: string;
}
