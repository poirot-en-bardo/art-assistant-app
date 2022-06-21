import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate} from '@angular/router';
import {AuthorisationService} from '../services/authorisation.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';


@Injectable()
export class AuthorisationGuard implements CanActivate {
  constructor(private authorisationService: AuthorisationService) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const allowedRoles = route.data['neededRoles'];
    let user = this.authorisationService.getSignedInUser()?.pipe()
    let roles = this.authorisationService.getSignedInUser()?.pipe(map(
      (response) => {
        return allowedRoles.some((item: string) => response.roles.includes(item));
      }));
    if (roles) {
      return true;
    } else return false;
  }
}
