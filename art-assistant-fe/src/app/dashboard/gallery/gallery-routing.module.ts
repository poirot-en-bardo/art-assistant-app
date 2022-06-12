import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthorisationGuard} from '../../core/guards/authorisation.guard';
import {GalleryComponent} from "./gallery.component";
import {RoomComponent} from "./room/room.component";

const routes: Routes = [
  //TODO change guard role
  {
    path: ':galleryId', component: GalleryComponent, canActivate: [AuthorisationGuard], data: {neededRoles: ['ROLE_USER','ROLE_ADMIN']},

    children: [
      { path: 'room/:roomId', component: RoomComponent},
    ]

  },

];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule]
})
export class GalleryRoutingModule {
}
