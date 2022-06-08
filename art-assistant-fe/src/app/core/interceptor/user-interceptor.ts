import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthorisationService } from '../services/authorisation.service';
import { environment } from '../../../environments/environment';
import { AuthorisationEnum } from '../constants/authorisation.constants';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private authorizationService: AuthorisationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const sessionId = this.authorizationService.getCurrentSessionId();
    const isAuthUrl = request.url.startsWith(environment.apiBaseUrl);

    if (sessionId && !isAuthUrl) {
      request = request.clone({headers: request.headers.set(AuthorisationEnum.SESSION_ID, sessionId)});
    }

    return next.handle(request);
  }
}

