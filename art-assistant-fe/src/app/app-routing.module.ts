import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'museums', pathMatch: 'full'},
  { path: 'museums', loadChildren: ()=> import('./dashboard/museums/museums.module').then(m => m.MuseumsModule)},
  // { path: 'profile', loadChildren: ()=> import('./dashboard/profile/profile.module').then(m => m.ProfileModule)},
  // { path: 'settings',loadChildren: ()=> import('./dashboard/settings/settings.module').then(m => m.SettingsModule)},
  // { path: 'groups', loadChildren: ()=> import('./dashboard/group/group.module').then(m => m.GroupModule)},
  // { path: 'notifications', loadChildren: ()=> import('./dashboard/notifications/notifications.module').then(m => m.NotificationsModule)},
  // { path: 'admin', loadChildren: ()=> import('./dashboard/admin/admin.module').then(m => m.AdminModule)},
  // { path: 'explore', loadChildren: ()=> import('./dashboard/explore/explore.module').then(m => m.ExploreModule)},
  // { path: 'authentication', loadChildren: ()=> import('./dashboard/authentication/authentication.module').then(m => m.AuthenticationModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
