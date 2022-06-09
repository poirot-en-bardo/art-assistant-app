import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {CoreModule} from "./core/core.module";
import {AppRoutingModule} from "./app-routing.module";
import {NgxsModule} from "@ngxs/store";
import {environment} from "../environments/environment";
import {appStates} from "./shared/redux/app.states";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {SharedModule} from "./shared/shared.module";
import { ProfileComponent } from './dashboard/profile/profile.component';
import { FavouritesComponent } from './dashboard/favourites/favourites.component';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    NgbModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    CoreModule,
    AppRoutingModule,
    BrowserModule,
    NgxsModule.forRoot([...appStates], {
      developmentMode: !environment.production
    }),
    SharedModule,
  ],
  exports: [],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
