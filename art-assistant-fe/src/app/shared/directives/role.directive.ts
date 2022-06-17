import { Directive, Input, OnInit, TemplateRef, ViewContainerRef } from '@angular/core';
import { AuthorisationService } from '../../core/services/authorisation.service';

@Directive({
  selector: '[hasRoles]'
})
export class RoleDirective implements OnInit {
  @Input() hasRoles: string[];

  constructor(
    private authorizationService: AuthorisationService,
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
  ) {
  }

  ngOnInit() {
    this.authorizationService.getSignedInUser()?.subscribe(
      ((response) => {
        if (this.hasRoles.filter(role => response.roles.includes(role)).length > 0) {
          this.viewContainer.createEmbeddedView(this.templateRef);
        } else {
          this.viewContainer.clear();
        }
      }));
  };
}

