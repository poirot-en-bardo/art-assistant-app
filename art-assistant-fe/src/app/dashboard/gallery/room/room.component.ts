import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
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
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CommentForm} from "./comments/add-comment/comment-form";

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

  public commentForm: FormGroup;
  @ViewChild("inputComment") inputComment: ElementRef;



  constructor(private roomService: RoomService, private artworkService: ArtworkService,
              private route: ActivatedRoute, private artistModalService: ArtistViewModalService,
              private commentService: CommentService, private formBuilder: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.index = 0;
    this.commentForm = this.formBuilder.group({
        [CommentForm.MESSAGE]: ['', Validators.required]
      }
    )
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

  public get CommentForm(): typeof CommentForm {
    return  CommentForm;
  }

  addComment(): void {
    let comment: CommentModel;
    // comment.userId
    // this.store.dispatch(new AddComment(this.item.id, this.commentForm.value))
    // this.item.noOfComments += 1
  }

  reset() {
    this.inputComment.nativeElement.value = '';
  }

}
