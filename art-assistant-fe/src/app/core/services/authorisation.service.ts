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
    const jwt = this.localStorageService.getItem(this.authorisationConstants.JWT);
    if(jwt) {
      return this.http.get<AuthoriseResponseModel>(`${environment.apiBaseUrl}/${AuthenticationApiConstants.API_AUTHORIZE_URL}/${jwt}`);
    }
    else return null;
  }

  getCurrentSessionJwt() {

    return this.localStorageService.getItem(this.authorisationConstants.JWT);
  }

}
