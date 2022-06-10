import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthorisationGuard} from '../../core/guards/authorisation.guard';
import {GalleryComponent} from "./gallery.component";

const routes: Routes = [
  //TODO change guard role
  {path: ':galleryId', component: GalleryComponent, canActivate: [AuthorisationGuard], data: {neededRoles: ['GUEST']},},

];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule]
})
export class GalleryRoutingModule {
}
