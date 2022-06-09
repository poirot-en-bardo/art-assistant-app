import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'museums', pathMatch: 'full'},
  { path: 'museums', loadChildren: ()=> import('./dashboard/museums/museums.module').then(m => m.MuseumsModule)},
  { path: 'random_artwork', loadChildren: ()=> import('./dashboard/random-art/random-art.module').then(m => m.RandomArtModule)},
  { path: 'profile', loadChildren: ()=> import('./dashboard/profile/profile.module').then(m => m.ProfileModule)},
  { path: 'favourites', loadChildren: ()=> import('./dashboard/favourites/favourites.module').then(m => m.FavouritesModule)},
  { path: 'authentication', loadChildren: ()=> import('./dashboard/authentication/authentication.module').then(m => m.AuthenticationModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
