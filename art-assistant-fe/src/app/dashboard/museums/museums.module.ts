import { NgModule } from '@angular/core';
import { CoreModule } from '../../core/core.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import {MuseumsComponent} from "./museums.component";
import {MuseumsRoutingModule} from "./museums-routing.module";
import { MuseumPageComponent } from './museum-page/museum-page.component';

@NgModule({
  declarations: [
    MuseumsComponent,
    MuseumPageComponent,
  ],
  imports: [
    CoreModule,
    FormsModule,
    MuseumsRoutingModule,
    CommonModule,
    SharedModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: []
})

export class MuseumsModule {

}
