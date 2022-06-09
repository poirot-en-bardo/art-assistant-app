import {Component, OnInit} from '@angular/core';
import {Observable, takeUntil} from "rxjs";
import {ToasterModel} from "./shared/models/toaster.model";
import {Router} from "@angular/router";
import { Select } from '@ngxs/store';
import {AuthorisationEnum} from "./core/constants/authorisation.constants";
import {AuthenticationRoutesConstants} from "./shared/constants/authentication-routes.constants";
import {LocalStorageService} from "./core/services/local-storage.service";
import {ToasterState} from "./shared/redux/toaster/toaster.state";
import {BaseComponent} from "./core/components/base/base.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent extends BaseComponent implements OnInit {

  title = 'art-assistant-fe';
  @Select(ToasterState.getToasterConfig)
  toasterConfig$: Observable<ToasterModel>;
  toasterConfig: ToasterModel;

  IMG_SRC = '/assets/icons/museum_icon.png';

  constructor(private router: Router, private localStorageService: LocalStorageService) {
    super();
  }

  ngOnInit(): void {
    // if (!this.localStorageService.getItem(AuthorisationEnum.SESSION_ID)) {
    //   this.router.navigate([AuthenticationRoutesConstants.HOME]);
    // }
    this.getToasterConfig();
  }

  getToasterConfig(): void {
    this.toasterConfig$.pipe(takeUntil(this.unsubscribe$)).subscribe((toasterConfig) => {
      if (toasterConfig) {
        this.toasterConfig = toasterConfig;
      }
    })
  }


}
