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
    const role = route.data['neededRoles'][0] as string;
    return this.authorisationService.getSignedInUser().pipe(map(
      (response) => {
        return response.roles.includes(role);
      }));
  }
}
