import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorMessagesComponent } from './components/error-messages/error-messages.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthorisationService } from '../core/services/authorisation.service';
import { ToasterComponent } from './components/toaster/toaster.component';
import { AuthenticationService } from './services/authentication.service';
import {FirstKeyPipe} from "./pipes/first-key.pipe";
import {RoleDirective} from "./directives/role.directive";
import {MuseumService} from "./services/museum.service";

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
  ],
  declarations: [
    ErrorMessagesComponent,
    FirstKeyPipe,
    RoleDirective,
    ToasterComponent
  ],
  exports: [
    ErrorMessagesComponent,
    FirstKeyPipe,
    RoleDirective,
    ToasterComponent
  ],
  providers: [
    AuthorisationService,
    AuthenticationService,
    MuseumService
  ],
  bootstrap: []
})

export class SharedModule {
}
