import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AuthorisationGuard } from '../../core/guards/authorisation.guard';
import {ProfileComponent} from "./profile.component";

const routes: Routes = [
  {path: '', component: ProfileComponent, canActivate: [AuthorisationGuard], data: {neededRoles: ['USER']}},
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ProfileRoutingModule {
}
