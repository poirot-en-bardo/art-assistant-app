import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import {ArtworkModel} from "../models/Artwork.model";
import {ArtworkApiConstants} from "../../core/constants/Artwork-api.constants";

@Injectable()
export class ArtworkService {

  constructor(private http: HttpClient) {
  }

  public getArtworksByRoomId(roomId: number): Observable<ArtworkModel[]> {
    return this.http.get<ArtworkModel[]>(
      `${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORKS_ALL}?roomId=${roomId}`);
  }

  public getArtworkById(artworkId: number): Observable<ArtworkModel> {
    return this.http.get<ArtworkModel>(`${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORK}/${artworkId}`);
  }

  public updateArtwork(artwork: ArtworkModel, artworkId: number): Observable<any> {
    return this.http.put(`${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORK}/${artworkId}`, artwork);
  }

  public addArtwork(artwork: ArtworkModel): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}/${ArtworkApiConstants.API_ARTWORK}`, artwork);
  }

}
