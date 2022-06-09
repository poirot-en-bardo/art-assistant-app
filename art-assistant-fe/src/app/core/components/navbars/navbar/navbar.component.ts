import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarRoutesConstants } from '../../../../shared/constants/navbar-routes.constants';
import { LocalStorageService } from '../../../services/local-storage.service';
import { AuthorisationEnum } from '../../../constants/authorisation.constants';
import { Select, Store } from '@ngxs/store';
import { Observable } from 'rxjs';
import { UserState } from '../../../../shared/redux/user/user.state';
import { AuthoriseResponseModel } from '../../../../shared/models/authorise-response.model';
import { takeUntil } from 'rxjs/operators';
import { BaseComponent } from '../../base/base.component';
import { GetLoggedUser, RemoveLoggedUser } from '../../../../shared/redux/user/user.action';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent extends BaseComponent implements OnInit {
  @Select(UserState.getLoggedUser)
  private loggedUser$: Observable<AuthoriseResponseModel>;
  loggedUser: AuthoriseResponseModel;

  currentUrl = '';
  navigatedTab = NavbarRoutesConstants;
  personAvatar = '';

  constructor(private store: Store, private router: Router, private localStorageService: LocalStorageService) {
    super();
  }

  ngOnInit() {
    this.router.events.pipe(takeUntil(this.unsubscribe$)).subscribe((_) => {
      this.currentUrl = this.router.url.toString();
    });

    this.store.dispatch(new GetLoggedUser());
    this.loggedUser$.pipe(takeUntil(this.unsubscribe$)).subscribe((userModel) => {
      if (userModel) {
        this.loggedUser = userModel;
      }
    });
  }

  onTabSelected(newTab: NavbarRoutesConstants) {
    this.currentUrl = newTab;
    this.router.navigate([newTab]);
  }

  goToProfile() {
    this.router.navigate([`/profile/${this.loggedUser.id}`]);
  }

  signOut() {
    this.localStorageService.removeItem(AuthorisationEnum.SESSION_ID);
    this.store.dispatch(new RemoveLoggedUser());
    window.location.reload();
  }
}
