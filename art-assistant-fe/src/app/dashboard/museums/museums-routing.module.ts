import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MuseumsComponent} from './museums.component';
import {AuthorisationGuard} from '../../core/guards/authorisation.guard';
import {MuseumPageComponent} from "./museum-page/museum-page.component";

const routes: Routes = [
  {path: ':museumId', component: MuseumPageComponent, canActivate: [
      AuthorisationGuard
    ],
    data: { neededRoles: ['GUEST'] }},
  {path: '', component: MuseumsComponent, canActivate: [AuthorisationGuard], data: {neededRoles: ['GUEST']},}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule]
})
export class MuseumsRoutingModule {
}
