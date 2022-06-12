import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate } from '@angular/router';
import { AuthorisationService } from '../services/authorisation.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable()
export class AuthorisationGuard implements CanActivate {
  constructor(private authorisationService: AuthorisationService) {
  }

  canActivate(route: ActivatedRouteSnapshot): Observable<boolean> {
    const allowedRoles = route.data['neededRoles'];
    return this.authorisationService.getSignedInUser().pipe(map(
      (response) => {
        return allowedRoles.some((item: string) => response.roles.includes(item));
      }));
  }
}
