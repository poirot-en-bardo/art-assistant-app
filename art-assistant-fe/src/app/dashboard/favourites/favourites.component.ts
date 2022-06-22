import {Component, OnInit} from '@angular/core';
import {Select, Store} from "@ngxs/store";
import {FavouriteState} from "../../shared/redux/favourites/favourites.state";
import {Observable, takeUntil} from "rxjs";
import {FavouriteArtworkModel} from "../../shared/models/favourite-artwork.model";
import {GetFavouriteArtworks, RemoveFavouriteArtwork} from "../../shared/redux/favourites/favourites.action";
import {BaseComponent} from "../../core/components/base/base.component";
import {ImageUtils} from "../../shared/utils/image.utils";

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.scss']
})
export class FavouritesComponent extends BaseComponent implements OnInit {
  @Select(FavouriteState.getFavouriteArtworks)
  private favourites$: Observable<FavouriteArtworkModel[]>;
  favourites: FavouriteArtworkModel[];


  constructor(private store: Store) {
    super();
  }

  ngOnInit(): void {
    this.store.dispatch(new GetFavouriteArtworks());
    this.favourites$.pipe(takeUntil(this.unsubscribe$)).subscribe((favourites) => {
      if (favourites) {
        this.favourites = favourites;
      }
    });
  }
  removeFavourite(id: number) {
    if (confirm('Are you sure you want to remove the artwork?')) {
      this.store.dispatch(new RemoveFavouriteArtwork(id));
    }
  }

}
