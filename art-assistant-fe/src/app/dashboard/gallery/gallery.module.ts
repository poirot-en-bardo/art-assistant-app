import { NgModule } from '@angular/core';
import { CoreModule } from '../../core/core.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import {GalleryRoutingModule} from "./gallery-routing.module";
import { RoomComponent } from './room/room.component';

@NgModule({
  declarations: [
    RoomComponent
  ],
  imports: [
    CoreModule,
    FormsModule,
    GalleryRoutingModule,
    CommonModule,
    SharedModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: []
})

export class GalleryModule {

}
