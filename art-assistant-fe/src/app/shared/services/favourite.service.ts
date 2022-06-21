import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, takeUntil} from "rxjs";
import {FavouriteArtworkModel} from "../models/favourite-artwork.model";
import {environment} from "../../../environments/environment";
import {FavouriteApiConstants} from "../../core/constants/favourite-api.constants";
import {Select, Store} from "@ngxs/store";
import {UserState} from "../redux/user/user.state";
import {AuthoriseResponseModel} from "../models/authorise-response.model";
import {GetLoggedUser} from "../redux/user/user.action";
import {BaseComponent} from "../../core/components/base/base.component";

@Injectable()
export class FavouriteService extends BaseComponent {

  @Select(UserState.getLoggedUser)
  private loggedUser$: Observable<AuthoriseResponseModel>;
  loggedUser: AuthoriseResponseModel;


  constructor(private http: HttpClient, private store: Store) {
    super();
    this.store.dispatch(new GetLoggedUser());
    this.loggedUser$.pipe(takeUntil(this.unsubscribe$)).subscribe((userModel) => {
      if (userModel) {
        this.loggedUser = userModel;
      }
    });
  }


  public getFavouriteArtworks(): Observable<FavouriteArtworkModel[]> {
    return this.http.get<FavouriteArtworkModel[]>(`${environment.apiBaseUrl}/${FavouriteApiConstants.API_GET_FAVOURITES}/${this.loggedUser?.id}`)
  }

  public addFavouriteArtwork(artworkId: number): Observable<any> {
    let requestObject = {
      userId: this.loggedUser.id,
      artworkId: artworkId
    }
    return this.http.post(`${environment.apiBaseUrl}/${FavouriteApiConstants.API_POST_FAVOURITE}`,requestObject);
  }

  public deleteFavourite(artworkId: number): Observable<any> {
    return this.http.delete(`${environment.apiBaseUrl}/${FavouriteApiConstants.API_POST_FAVOURITE}?userId=${this.loggedUser.id}&artworkId=${artworkId}`);
  }
}
