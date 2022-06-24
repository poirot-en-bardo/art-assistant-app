import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import {ArtworkModel} from "../models/Artwork.model";
import {ArtworkApiConstants} from "../../core/constants/Artwork-api.constants";
import {ArtistModel} from "../models/artist.model";
import {ArtistApiConstants} from "../../core/constants/artist-api.constants";

@Injectable()
export class ArtistService {

  constructor(private http: HttpClient) {
  }

  public getArtists(): Observable<ArtistModel[]> {
    return this.http.get<ArtistModel[]>(
      `${environment.apiBaseUrl}/${ArtistApiConstants.API_ARTISTS_ALL}`);
  }

}
