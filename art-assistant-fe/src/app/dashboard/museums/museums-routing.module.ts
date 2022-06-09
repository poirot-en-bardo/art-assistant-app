import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MuseumsComponent} from './museums.component';
import {AuthorisationGuard} from '../../core/guards/authorisation.guard';
import {MuseumPageComponent} from "./museum-page/museum-page.component";

const routes: Routes = [
  {path: '', component: MuseumsComponent, canActivate: [AuthorisationGuard], data: {neededRoles: ['GUEST']},},
  {path: ':museumId', component: MuseumPageComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class MuseumsRoutingModule {
}
