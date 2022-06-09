import { NgModule } from '@angular/core';
import { CoreModule } from '../../core/core.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import {RandomArtComponent} from "./random-art.component";
import {RandomArtRoutingModule} from "./random-art-routing.module";

@NgModule({
  declarations: [
    RandomArtComponent,
  ],
  imports: [
    CoreModule,
    FormsModule,
    RandomArtRoutingModule,
    CommonModule,
    SharedModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [RandomArtComponent]
})

export class RandomArtModule {

}
