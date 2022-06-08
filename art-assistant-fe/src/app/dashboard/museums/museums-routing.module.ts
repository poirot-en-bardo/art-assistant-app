import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { MuseumsComponent } from './museums.component';
import { AuthorisationGuard } from '../../core/guards/authorisation.guard';

const routes: Routes = [
  {path: '', component: MuseumsComponent, canActivate: [AuthorisationGuard], data: {neededRoles: ['USER']}},
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class MuseumsRoutingModule {
}
