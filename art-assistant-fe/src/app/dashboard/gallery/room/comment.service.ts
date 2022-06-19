import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../../environments/environment";
import {CommentModel} from "../../../shared/models/comment.model";
import {CommentApiConstants} from "../../../core/constants/comment-api.constants";


@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) {}

  public getCommentsByArtworkId(artworkId: number): Observable<CommentModel[]> {
    return this.http.get<CommentModel[]>(`${environment.apiBaseUrl}/${CommentApiConstants.API_GET_COMMENTS}?artworkId=${artworkId}`);
  }

  addComment(body: CommentModel): Observable<CommentModel> {
    return this.http.post<CommentModel>(`${environment.apiBaseUrl}/${CommentApiConstants.API_POST_COMMENT}`, body);
  }
}
