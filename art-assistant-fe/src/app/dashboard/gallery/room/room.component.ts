import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../../core/components/base/base.component";
import {RoomService} from "../../../shared/services/room.service";
import {ArtworkService} from "../../../shared/services/artwork.service";
import {ActivatedRoute} from "@angular/router";
import {RoomModel} from "../../../shared/models/room.model";
import {ArtworkModel} from "../../../shared/models/artwork.model";
import {take, takeUntil} from "rxjs";
import {ImageUtils} from '../../../shared/utils/image.utils';
import {ArtistViewModalService} from "../../../shared/services/artist-view-modal.service";
import {CommentService} from "./comment.service";
import {CommentModel} from "../../../shared/models/comment.model";
import {CommentListModel} from "../../../shared/models/comment-list.model";

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent extends BaseComponent implements OnInit {

  room: RoomModel;
  artworks: ArtworkModel[]=[];
  index: number;
  commentList: any = [];


  constructor(private roomService: RoomService, private artworkService: ArtworkService,
              private route: ActivatedRoute, private artistModalService: ArtistViewModalService,
              private commentService: CommentService) {
    super();
  }

  ngOnInit(): void {
    this.index = 0;
    this.getData();
  }

  getData() {
    this.route.params.pipe(takeUntil(this.unsubscribe$)).subscribe(params => {
      this.roomService.getRoomById(params['roomId']).pipe(takeUntil(this.unsubscribe$))
        .subscribe(room => {
          this.room = room;
          this.getArtworks();
        })
    })
  }

  getArtworks() {
    this.artworkService.getArtworksByRoomId(this.room.id).pipe(takeUntil(this.unsubscribe$))
      .subscribe(artworks => {
          this.artworks = artworks;
          this.artworks.forEach((artwork, index) => {
            if (artwork.imagePath !== null) {
              artwork.imagePath = ImageUtils.appendImageType(artwork.imagePath);
            }
            this.commentService.getCommentsByArtworkId(artwork.id).pipe(takeUntil(this.unsubscribe$)).subscribe(
              (items) => {
                if (items) {
                  this.commentList.push(items);
                }
                console.log(this.commentList[1]); // commentList[index] pt comments
              }
            )
          })
        }
      )
  }

  // getComments() {
  //   this.commentService.getCommentsByArtworkId(this.artworks[this.index].id).pipe(takeUntil(this.unsubscribe$)).subscribe(
  //     (items) => {
  //       this.commentList = items;
  //       console.log(this.commentList);
  //     });
  // }

  prev() {
    if (this.index < this.artworks.length - 1) {
      this.index++;
    }
  }

  next() {
    if (this.index > 0) {
      this.index--;
    }
  }

  viewArtistModal() {
    this.artworkService.getArtistByArtworkId(this.artworks[this.index].id).pipe(takeUntil(this.unsubscribe$)).subscribe(
      response => {
        this.artistModalService.openModal(response);
      }
    )
  }

}
