import { Injectable } from '@angular/core';
import { UserSignInModel, UserSignUpModel } from '../models/user.model';
import { environment } from '../../../environments/environment';
import { AuthenticationApiConstants } from '../../core/constants/authentication-api.constants';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthoriseRequestModel } from '../models/authorise-request.model';
import { AuthoriseResponseModel } from '../models/authorise-response.model';
import { UserManagementConstants } from '../../core/constants/user-management.constants';

@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient) {
  }

  public signIn(user: UserSignInModel): Observable<AuthoriseRequestModel> {
    return this.http.post<AuthoriseRequestModel>(`${environment.apiBaseUrl}/${AuthenticationApiConstants.API_SIGNIN_URL}/`, user);
  }

  public signUp(user: UserSignUpModel): Observable<AuthoriseRequestModel> {
    return this.http.post<AuthoriseRequestModel>(`${environment.apiBaseUrl}/${AuthenticationApiConstants.API_SIGNUP_URL}/`, user);
  }

  // public createPreferencesForNewUser(): Observable<any> {
  //   return this.http.post(`${environment.apiBaseUrl}/${NotificationsApiUrlsConstants.API_PREFERENCE_URL}/`, null)
  // }

  // public createGjPrivacyEntry(): Observable<any> {
  //   const gjPrivacy: GoodJobPrivacyModel = {
  //     gjPrivacy: GoodJobPrivacy.PUBLIC
  //   }
  //   return this.http.post<void>(`${environment.goodJobBaseUrl}/${GjApiUrlsConstants.API_GOODJOB_PRIVACY}`, gjPrivacy);
  // }

  getUserById(userId: string) {
    return this.http.get<AuthoriseResponseModel> (`${environment.apiBaseUrl}/${UserManagementConstants.API_USER_BY_ID}/${userId}` );
  }
}
