import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AuthorisationGuard } from '../../core/guards/authorisation.guard';
import {RandomArtComponent} from "./random-art.component";

const routes: Routes = [
  {path: '', component: RandomArtComponent,
    canActivate: [AuthorisationGuard], data: {neededRoles: ['ROLE_USER','ROLE_ADMIN']}
    },];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class RandomArtRoutingModule {
}
