import { Component, OnInit } from '@angular/core';
import {BaseComponent} from "../../core/components/base/base.component";
import {Select, Store} from "@ngxs/store";
import {UserState} from "../../shared/redux/user/user.state";
import {Observable, takeUntil} from "rxjs";
import {AuthoriseResponseModel} from "../../shared/models/authorise-response.model";
import {GetLoggedUser} from "../../shared/redux/user/user.action";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent extends BaseComponent implements OnInit {
  @Select(UserState.getLoggedUser)
  private loggedUser$: Observable<AuthoriseResponseModel>;
  loggedUser: AuthoriseResponseModel;

  admin: boolean = false;

  constructor(private store: Store) {
    super();
  }

  ngOnInit(): void {
    this.store.dispatch(new GetLoggedUser());
    this.loggedUser$.pipe(takeUntil(this.unsubscribe$)).subscribe((userModel) => {
      if (userModel) {
        this.loggedUser = userModel;
        if(this.loggedUser.roles.includes("ROLE_ADMIN")){
          this.admin = true;
        }
      }
    });
  }

}
