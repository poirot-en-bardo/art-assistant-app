import { NgModule } from '@angular/core';
import { CoreModule } from '../../core/core.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import {FavouritesComponent} from "./favourites.component";
import {FavouritesRoutingModule} from "./favourites-routing.module";

@NgModule({
  declarations: [
    FavouritesComponent,
  ],
  imports: [
    CoreModule,
    FormsModule,
    FavouritesRoutingModule,
    CommonModule,
    SharedModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [FavouritesComponent]
})

export class FavouritesModule {

}
