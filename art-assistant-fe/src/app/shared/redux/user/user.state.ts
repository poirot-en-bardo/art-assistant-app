import {Injectable} from '@angular/core';
import {Action, Selector, State, StateContext} from '@ngxs/store';
import {tap} from 'rxjs/operators';
import {AuthoriseResponseModel} from '../../models/authorise-response.model';
import {AuthorisationService} from '../../../core/services/authorisation.service';
import {GetLoggedUser, RemoveLoggedUser} from './user.action';

export interface UserStateModel {
  loggedUser?: AuthoriseResponseModel
}

export const defaults = {
  loggedUser: undefined
};

@State<UserStateModel>({
  name: 'user',
  defaults,
})
@Injectable()
export class UserState {

  constructor(private authorizationService: AuthorisationService) {
  }

  @Selector()
  static getLoggedUser(userStateModel: UserStateModel) {
    return userStateModel.loggedUser;
  }

  @Action(GetLoggedUser)
  getLoggedUser({getState, patchState}: StateContext<UserStateModel>) {
    if (getState().loggedUser) {
      return patchState({loggedUser: getState().loggedUser});
    }

    return this.authorizationService.getSignedInUser()?.pipe(tap((userModel) => {
      if (userModel.roles.includes("ROLE_ADMIN")) {
        userModel.admin = true;
      }
      patchState({loggedUser: userModel});
    }));
  }

  @Action(RemoveLoggedUser)
  removeLoggedUser({getState, patchState}: StateContext<UserStateModel>) {
    if (getState().loggedUser) {
      return patchState({loggedUser: undefined});
    }
  }
}
