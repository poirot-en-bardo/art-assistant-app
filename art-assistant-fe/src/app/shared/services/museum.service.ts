import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import {MuseumModel} from "../models/museum.model";
import {MuseumApiConstants} from "../../core/constants/museum-api.constants";

@Injectable()
export class MuseumService {

  constructor(private http: HttpClient) {
  }

  public getMuseums(): Observable<MuseumModel[]> {
    return this.http.get<MuseumModel[]>(`${environment.apiBaseUrl}/${MuseumApiConstants.API_MUSEUMS_ALL_OPEN}`);
  }

  public getMuseumById(museumId: number): Observable<MuseumModel> {
    return this.http.get<MuseumModel>(`${environment.apiBaseUrl}/${MuseumApiConstants.API_MUSEUM_OPEN}/${museumId}`);
  }

  public updateMuseum(museum: MuseumModel, museumId: number): Observable<any> {
    return this.http.put(`${environment.apiBaseUrl}/${MuseumApiConstants.API_MUSEUM_OPEN}/${museumId}`, museum);
  }
}
