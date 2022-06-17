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
import {GalleryService} from "./services/gallery.service";
import {CountryService} from "./services/country.service";
import {RoomService} from "./services/room.service";
import {ArtworkService} from "./services/artwork.service";
import { ArtistViewModalComponent } from './modals/artist-view-modal/artist-view-modal.component';
import {ArtistViewModalService} from "./services/artist-view-modal.service";

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
    ToasterComponent,
    ArtistViewModalComponent
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
    CountryService,
    MuseumService,
    GalleryService,
    RoomService,
    ArtworkService,
    ArtistViewModalService
  ],
  bootstrap: []
})

export class SharedModule {
}
