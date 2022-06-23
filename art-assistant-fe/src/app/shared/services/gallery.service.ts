import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import {GalleryModel} from "../models/gallery.model";
import {GalleryApiConstants} from "../../core/constants/gallery-api.constants";

@Injectable()
export class GalleryService {

  constructor(private http: HttpClient) {
  }

  public getGalleriesByMuseumId(museumId: number): Observable<GalleryModel[]> {
    return this.http.get<GalleryModel[]>(
      `${environment.apiBaseUrl}/${GalleryApiConstants.API_GALLERIES_OPEN}?museumId=${museumId}`);
  }

  public getGalleryById(galleryId: number): Observable<GalleryModel> {
    return this.http.get<GalleryModel>(`${environment.apiBaseUrl}/${GalleryApiConstants.API_GALLERY_USER}/${galleryId}`);
  }

  public updateGallery(gallery: GalleryModel, galleryId: number): Observable<any> {
    return this.http.put(`${environment.apiBaseUrl}/${GalleryApiConstants.API_GALLERY_USER}/${galleryId}`, gallery);
  }

  public addGallery(gallery: GalleryModel): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}/${GalleryApiConstants.API_GALLERY_ADMIN}`, gallery);
  }

}
