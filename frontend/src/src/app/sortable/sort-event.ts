import {SortDirection} from "./sortable-header.directive";

export interface SortEvent {
  column: string;
  direction: SortDirection;
}
