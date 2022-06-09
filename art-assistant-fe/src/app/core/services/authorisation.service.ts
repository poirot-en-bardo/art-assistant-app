import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthoriseResponseModel } from '../../shared/models/authorise-response.model';
import { AuthorisationEnum } from '../constants/authorisation.constants';
import { LocalStorageService } from './local-storage.service';
import { environment } from '../../../environments/environment';
import {AuthenticationApiConstants} from "../constants/authentication-api.constants";

@Injectable()
export class AuthorisationService {

  authorisationConstants = AuthorisationEnum;

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) {
  }

  getSignedInUser() {
    // const sessionId = this.localStorageService.getItem(this.authorisationConstants.SESSION_ID);
    const sessionId = "9a38e007-149f-4f67-9c4f-4ccb2c6ee1db";
    // return this.http.get<AuthoriseResponseModel>(`${environment.apiBaseUrl}/${AuthenticationApiConstants.API_AUTHORIZE_URL}/${sessionId}`);
    return this.http.get<AuthoriseResponseModel>(`${environment.apiBaseUrl}/${AuthenticationApiConstants.API_AUTHORIZE_URL}?sessionId=${sessionId}`);
  }

  getCurrentSessionId() {
    return this.localStorageService.getItem(this.authorisationConstants.SESSION_ID);
  }

}
