import { NgModule } from '@angular/core';
import { CoreModule } from '../../core/core.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import {MuseumsComponent} from "./museums.component";
import {MuseumsRoutingModule} from "./museums-routing.module";

@NgModule({
  declarations: [
    MuseumsComponent,
  ],
  imports: [
    CoreModule,
    FormsModule,
    MuseumsRoutingModule,
    CommonModule,
    SharedModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [MuseumsComponent]
})

export class MuseumsModule {

}
