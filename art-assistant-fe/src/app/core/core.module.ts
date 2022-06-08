import {NgModule} from "@angular/core";
import {AuthorisationService} from "./services/authorisation.service";
import {AuthorisationGuard} from "./guards/authorisation.guard";
import {LocalStorageService} from "./services/local-storage.service";
import {SharedModule} from "../shared/shared.module";
import {HttpInterceptorModule} from "./http-interceptor.module";
import {RouterModule} from "@angular/router";
import {CommonModule} from "@angular/common";
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";
import {NavbarComponent} from "./components/navbars/navbar/navbar.component";
import {MobileNavbarComponent} from "./components/navbars/mobile-navbar/mobile-navbar.component";

@NgModule({
  imports: [
    CommonModule,
    NgbDropdownModule,
    RouterModule,
    HttpInterceptorModule,
    SharedModule
  ],
  declarations: [
    NavbarComponent,
    MobileNavbarComponent
  ],
  exports: [
    NavbarComponent,
    MobileNavbarComponent
  ],
  providers: [
    AuthorisationService, AuthorisationGuard, LocalStorageService
  ]
})
export class CoreModule {
}
