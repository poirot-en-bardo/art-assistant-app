import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import {RoomModel} from "../models/room.model";
import {RoomApiConstants} from "../../core/constants/room-api.constants";

@Injectable()
export class RoomService {

  constructor(private http: HttpClient) {
  }

  public getRoomsByGalleryId(galleryId: number): Observable<RoomModel[]> {
    return this.http.get<RoomModel[]>(`${environment.apiBaseUrl}/${RoomApiConstants.API_ROOMS_ALL_USER}?galleryId=${galleryId}`);
  }

  public getRoomById(roomId: number): Observable<RoomModel> {
    return this.http.get<RoomModel>(`${environment.apiBaseUrl}/${RoomApiConstants.API_ROOM_USER}/${roomId}`);
  }

  public updateRoom(room: RoomModel, roomId: number): Observable<any> {
    return this.http.put(`${environment.apiBaseUrl}/${RoomApiConstants.API_ROOM_USER}/${roomId}`, room);
  }
}
