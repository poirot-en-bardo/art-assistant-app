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
    // return this.http.get<AuthoriseResponseModel>(`${environment.apiBaseUrl}/${AuthenticationApiConstants.API_AUTHORIZE_URL}/${sessionId}`);
    // const jwt = this.localStorageService.getItem(this.authorisationConstants.JWT);
    // const jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4IiwiaWF0IjoxNjU1MDUwNTY3LCJleHAiOjE2NTY3Nzg1Njd9.8VUnAjKReKd4CKsYPTBNjJofRSkHmgloLlINUcAGTNfI1odlXM5WEKdIay2pMdBQM3212WvS7FX7PMDC0V3m6w";
    const jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNiIsImlhdCI6MTY1NTAzNTA0NiwiZXhwIjoxNjU2NzYzMDQ2fQ.vIj45AoP1nnVPR9mNLMC46-yghb26ST1Xo4rZo8_kVKuwsuaGCsPqkrcNIyo_3V_53PYJJ-7Dy26UWyVlmdw8g";
    // const jwt = null;
    return this.http.get<AuthoriseResponseModel>(`${environment.apiBaseUrl}/${AuthenticationApiConstants.API_AUTHORIZE_URL}/${jwt}`);
  }

  getCurrentSessionJwt() {

    return this.localStorageService.getItem(this.authorisationConstants.JWT);
  }

}
