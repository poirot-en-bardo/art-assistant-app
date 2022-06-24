import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import {ArtworkModel} from "../models/Artwork.model";
import {ArtworkApiConstants} from "../../core/constants/Artwork-api.constants";
import {ArtistModel} from "../models/artist.model";
import {CommentApiConstants} from "../../core/constants/comment-api.constants";

@Injectable()
export class ArtworkService {

  constructor(private http: HttpClient) {
  }

  public getArtworksByRoomId(roomId: number): Observable<ArtworkModel[]> {
    return this.http.get<ArtworkModel[]>(
      `${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORKS_ALL_USER}?roomId=${roomId}`);
  }

  public getArtworkById(artworkId: number): Observable<ArtworkModel> {
    return this.http.get<ArtworkModel>(`${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORK_USER}/${artworkId}`);
  }

  public updateArtwork(artwork: ArtworkModel, artworkId: number): Observable<any> {
    return this.http.put(`${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORK_ADMIN}/${artworkId}`, artwork);
  }

  public addArtwork(artwork: ArtworkModel): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORK_ADMIN}`, artwork);
  }

  public getArtworkIds(): Observable<number[]> {
    return this.http.get<number[]>(`${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORK_IDS}`);
  }

  public getArtistByArtworkId(artworkId: number): Observable<ArtistModel> {
    return this.http.get<ArtistModel>(`${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTIST_ARTWORK_ID}/${artworkId}`)
  }

  public deleteArtworkById(artworkId: number): Observable<any> {
    return this.http.delete(`${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORK_ADMIN}/${artworkId}`);
  }
}
