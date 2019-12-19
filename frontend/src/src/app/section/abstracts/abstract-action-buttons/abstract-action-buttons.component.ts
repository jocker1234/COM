import {Component, Input, OnInit} from '@angular/core';
import {AbstractsService} from "../../../service/abstracts.service";
import {ResearchAbstract} from "../research-abstract";
import {CaseAbstract} from "../case-abstract";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-abstract-action-buttons',
  templateUrl: './abstract-action-buttons.component.html',
  styleUrls: ['./abstract-action-buttons.component.scss']
})
export class AbstractActionButtonsComponent implements OnInit {

  @Input()
  prefixUrl: string;
  @Input()
  type: string;
  @Input()
  insertable: boolean;

  @Input()
  research: ResearchAbstract;

  @Input()
  case: CaseAbstract;

  @Input()
  private id: number;

  constructor(private abstractService: AbstractsService, private router: Router,
              private location: Location) {
  }

  ngOnInit() {
  }

  checkStatus(): boolean {
    if (this.case !== undefined) {
      return this.case.status === 'SEND';
    } else if (this.research !== undefined) {
      return this.research.status === 'SEND';
    }
  }

  save() {
    console.log(this.research);
    if (this.prefixUrl.includes("case") === true) {
      this.case.type = 'C';
      this.abstractService.newCaseAbstract(this.case).subscribe(value => {
        this.router.navigate([this.prefixUrl + value.id]);
      });
    } else if (this.prefixUrl.includes("research") === true) {
      this.research.type = 'R';
      this.abstractService.newResearchAbstract(this.research).subscribe(value => {
        this.router.navigate([this.prefixUrl + value.id]);
      });
    }
  }

  saveAndSend() {
    if(this.insertable) {
      if (this.prefixUrl.includes("case") === true) {
        this.case.type = 'C';
        this.abstractService.newCaseAbstract(this.case).subscribe(value => {
          this.abstractService.sendCaseAbstract(value.id).subscribe(value1 => {
            this.router.navigate([this.prefixUrl + value.id]);
          });
        });
      } else if (this.prefixUrl.includes("research") === true) {
        this.research.type = 'R';
        this.abstractService.newResearchAbstract(this.research).subscribe(value => {
          this.abstractService.sendResearchAbstract(value.id).subscribe(value1 => {
            this.router.navigate([this.prefixUrl + value.id]);
          });
        });
      }
    }
  }

  send() {
    if (this.case !== undefined) {
      this.abstractService.sendCaseAbstract(this.case.id).subscribe();
    } else if (this.research !== undefined) {
      this.abstractService.sendResearchAbstract(this.research.id).subscribe();
    }
    window.location.reload();
  }

  delete() {
    if (this.case !== undefined) {
      this.abstractService.deleteCaseAbstract(this.case.id).subscribe();
    } else if (this.research !== undefined) {
      this.abstractService.deleteResearchAbstract(this.research.id).subscribe();
    }
    this.router.navigate(["/abstracts"]);
  }

  modifyAbstract() {
    this.router.navigate([this.prefixUrl + this.id + '/edit']);
  }

  cancel(): void {
    this.location.back();
  }

  saveModifyAndSend() {
    if (this.prefixUrl.includes("case") === true) {
      this.case.type = 'C';
      this.abstractService.saveCaseAbstract(this.id, this.case).subscribe(value => {
        this.abstractService.sendCaseAbstract(this.id).subscribe(value1 => {
          this.router.navigate([this.prefixUrl + value.id]);
        });
      });
    } else if (this.prefixUrl.includes("research") === true) {
      this.research.type = 'R';
      this.abstractService.saveResearchAbstract(this.id, this.research).subscribe(value => {
        this.abstractService.sendResearchAbstract(this.id).subscribe(value1 => {
          this.router.navigate([this.prefixUrl + value.id]);
        });
      });
    }
  }

  saveModify() {
    if (this.prefixUrl.includes("case") === true) {
      this.case.type = 'C';
      this.abstractService.saveCaseAbstract(this.id, this.case).subscribe(value => {
        this.router.navigate([this.prefixUrl + value.id]);
      });
    } else if (this.prefixUrl.includes("research") === true) {
      this.research.type = 'R';
      this.abstractService.saveResearchAbstract(this.id, this.research).subscribe(value => {
        this.router.navigate([this.prefixUrl + value.id]);
      });
    }
  }
}
