import {FavouriteArtworkModel} from "../../models/favourite-artwork.model";
import {Action, Selector, State, StateContext, Store} from "@ngxs/store";
import {Injectable} from "@angular/core";
import {FavouriteService} from "../../services/favourite.service";
import {AddFavouriteArtwork, GetFavouriteArtworks, RemoveFavouriteArtwork} from "./favourites.action";
import {tap} from "rxjs/operators";
import {ShowSuccessToaster} from "../toaster/toaster.action";
import {ImageUtils} from "../../utils/image.utils";

export interface FavouriteStateModel {
  favourites?: FavouriteArtworkModel[];
}

export const defaults = {
  favourites: undefined
};

@State<FavouriteStateModel>({
  name: 'favourites',
  defaults
})

@Injectable()
export class FavouriteState {

  constructor(private favouriteService: FavouriteService, private store: Store) {
  }

  @Selector()
  static getFavouriteArtworks(state: FavouriteStateModel) {
    return state.favourites;
  }

  @Action(GetFavouriteArtworks)
  getFavouriteArtworks({getState, patchState}: StateContext<FavouriteStateModel>) {
    const favourites = getState().favourites;
    if(favourites) {
      patchState({
        favourites
      })
    }
    return this.favouriteService.getFavouriteArtworks().pipe(
      tap((favourites: FavouriteArtworkModel[]) => {
        favourites.forEach(artwork => {
          if (artwork.imagePath !== null) {
            artwork.imagePath = ImageUtils.appendImageType(artwork.imagePath);
          }
        })
        patchState({favourites});
      })
    );
  }

  @Action(AddFavouriteArtwork)
  addFavouriteArtwork({getState, patchState}: StateContext<FavouriteStateModel>, action: AddFavouriteArtwork) {
    let favourites: FavouriteArtworkModel[] = JSON.parse(JSON.stringify(getState().favourites));

    return this.favouriteService.addFavouriteArtwork(action.id).pipe(
      tap((response) => {
        this.store.dispatch(new ShowSuccessToaster());
        favourites.push(response);
        patchState({favourites});
      })
    );
  }

  @Action(RemoveFavouriteArtwork)
  removeFavouriteArtwork({getState, patchState}: StateContext<FavouriteStateModel>,
                         action: RemoveFavouriteArtwork) {
    let favourites: FavouriteArtworkModel[] = JSON.parse(JSON.stringify(getState().favourites));

    return this.favouriteService.deleteFavourite(action.id).pipe(
      tap(() => {
        favourites = favourites.filter(artwork => artwork.id != action.id)
        patchState({
          favourites
        })
        this.store.dispatch(new ShowSuccessToaster());
      })
    )
  }
}
