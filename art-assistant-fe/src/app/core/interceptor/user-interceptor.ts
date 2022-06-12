import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthorisationService} from '../services/authorisation.service';
import {environment} from '../../../environments/environment';
import {AuthorisationEnum} from '../constants/authorisation.constants';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private authorizationService: AuthorisationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const sessionJwt = this.authorizationService.getCurrentSessionJwt();
    // const sessionJwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNiIsImlhdCI6MTY1NTAzNTA0NiwiZXhwIjoxNjU2NzYzMDQ2fQ.vIj45AoP1nnVPR9mNLMC46-yghb26ST1Xo4rZo8_kVKuwsuaGCsPqkrcNIyo_3V_53PYJJ-7Dy26UWyVlmdw8g";
    // const sessionJwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4IiwiaWF0IjoxNjU1MDUwNTY3LCJleHAiOjE2NTY3Nzg1Njd9.8VUnAjKReKd4CKsYPTBNjJofRSkHmgloLlINUcAGTNfI1odlXM5WEKdIay2pMdBQM3212WvS7FX7PMDC0V3m6w";
    // const sessionJwt = null;
    const isAuthUrl = request.url.startsWith(environment.apiBaseUrl);

    if (sessionJwt && !isAuthUrl) {
      // request = request.clone({headers: request.headers.set(AuthorisationEnum.JWT, sessionJwt)});
      request = request.clone({
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + sessionJwt
        })
      });
    }

    return next.handle(request);
  }
}

