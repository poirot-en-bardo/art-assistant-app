import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {GenreModel} from "../models/genre.model";
import {GenreApiConstants} from "../../core/constants/genre-api.constants";
import {ArtistModel} from "../models/artist.model";
import {ArtistApiConstants} from "../../core/constants/artist-api.constants";

@Injectable()
export class GenreService {

  constructor(private http: HttpClient) {
  }

  public getGenreById(genreId: number): Observable<GenreModel> {
    return this.http.get<GenreModel>(
      `${environment.apiBaseUrl}/${GenreApiConstants.API_GENRE_GET}/${genreId}`);
  }

  public getAllGenres(): Observable<GenreModel[]> {
    return this.http.get<GenreModel[]>(
      `${environment.apiBaseUrl}/${GenreApiConstants.API_GENRE_GET}`);
  }

}
