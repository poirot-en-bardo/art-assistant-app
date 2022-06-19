import { NgModule } from '@angular/core';
import { CoreModule } from '../../core/core.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import {GalleryRoutingModule} from "./gallery-routing.module";
import { RoomComponent } from './room/room.component';
import { CommentsComponent } from './room/comments/comments.component';
import {GalleryComponent} from "./gallery.component";

@NgModule({
  declarations: [
    RoomComponent,
    CommentsComponent
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
  bootstrap: [GalleryComponent]
})

export class GalleryModule {

}
