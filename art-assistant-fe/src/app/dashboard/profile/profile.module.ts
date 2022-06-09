import { NgModule } from '@angular/core';
import { CoreModule } from '../../core/core.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import {ProfileComponent} from "./profile.component";
import {ProfileRoutingModule} from "./profile-routing.module";

@NgModule({
  declarations: [
    ProfileComponent,
  ],
  imports: [
    CoreModule,
    FormsModule,
    ProfileRoutingModule,
    CommonModule,
    SharedModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [ProfileComponent]
})

export class ProfileModule {

}
