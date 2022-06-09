import { Component, OnInit } from '@angular/core';
import { NavbarRoutesConstants } from '../../../../shared/constants/navbar-routes.constants';
import { Router } from '@angular/router';
import { Select, Store } from '@ngxs/store';
import { Observable } from 'rxjs';
import { AuthoriseResponseModel } from '../../../../shared/models/authorise-response.model';
import { takeUntil } from 'rxjs/operators';
import { BaseComponent } from '../../base/base.component';
import { AuthorisationEnum } from '../../../constants/authorisation.constants';
import { LocalStorageService } from '../../../services/local-storage.service';
import { GetLoggedUser, RemoveLoggedUser } from '../../../../shared/redux/user/user.action';
import { UserState } from '../../../../shared/redux/user/user.state';


@Component({
  selector: 'app-mobile-navbar',
  templateUrl: './mobile-navbar.component.html',
  styleUrls: ['./mobile-navbar.component.scss']
})
export class MobileNavbarComponent extends BaseComponent implements OnInit {

  MUSEUM_SRC = '/assets/icons/museum_icon.png';

  // @Select(UserState.getLoggedUser)
  // private loggedUser$: Observable<AuthoriseResponseModel>;
  // loggedUser: AuthoriseResponseModel;

  currentUrl = '';
  navigatedTab = NavbarRoutesConstants;

  constructor(private router: Router, private store: Store, private localStorageService: LocalStorageService) {
    super();
  }

  ngOnInit() {
    this.router.events.pipe(takeUntil(this.unsubscribe$)).subscribe((_) => {
      this.currentUrl = this.router.url.toString();
    });

    // this.store.dispatch(new GetLoggedUser());
    // this.loggedUser$.pipe(takeUntil(this.unsubscribe$)).subscribe((userModel) => {
    //   if (userModel) {
    //     this.loggedUser = userModel;
    //   }
    // });
  }

  onTabSelected(newTab: string) {
    this.router.navigate([newTab]);
  }

  signOut() {
    this.localStorageService.removeItem(AuthorisationEnum.SESSION_ID);
    this.store.dispatch(new RemoveLoggedUser());
    window.location.reload();
  }
}
