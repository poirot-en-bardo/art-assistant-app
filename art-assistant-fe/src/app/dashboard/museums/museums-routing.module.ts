import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MuseumsComponent} from './museums.component';
import {AuthorisationGuard} from '../../core/guards/authorisation.guard';
import {MuseumPageComponent} from "./museum-page/museum-page.component";

const routes: Routes = [
  {path: ':museumId', component: MuseumPageComponent,
    // canActivate: [AuthorisationGuard],
    // data: { neededRoles: ['ROLE_USER','ROLE_ADMIN'] }
  },
  {path: '', component: MuseumsComponent,
    // canActivate: [AuthorisationGuard], data: {neededRoles: ['ROLE_USER','ROLE_ADMIN']}
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule]
})
export class MuseumsRoutingModule {
}
