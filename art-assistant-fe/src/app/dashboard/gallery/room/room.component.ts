import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {BaseComponent} from "../../../core/components/base/base.component";
import {RoomService} from "../../../shared/services/room.service";
import {ArtworkService} from "../../../shared/services/artwork.service";
import {ActivatedRoute} from "@angular/router";
import {RoomModel} from "../../../shared/models/room.model";
import {ArtworkModel} from "../../../shared/models/artwork.model";
import {Observable, take, takeUntil} from "rxjs";
import {ImageUtils} from '../../../shared/utils/image.utils';
import {ArtistViewModalService} from "../../../shared/services/artist-view-modal.service";
import {CommentService} from "./comment.service";
import {CommentModel} from "../../../shared/models/comment.model";
import {CommentListModel} from "../../../shared/models/comment-list.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CommentForm} from "./comments/add-comment/comment-form";
import {Select, Store} from "@ngxs/store";
import {UserState} from "../../../shared/redux/user/user.state";
import {AuthoriseResponseModel} from "../../../shared/models/authorise-response.model";
import {FavouriteState} from "../../../shared/redux/favourites/favourites.state";
import {FavouriteArtworkModel} from "../../../shared/models/favourite-artwork.model";
import {GetLoggedUser} from "../../../shared/redux/user/user.action";
import {
  AddFavouriteArtwork,
  GetFavouriteArtworks,
  RemoveFavouriteArtwork
} from "../../../shared/redux/favourites/favourites.action";
import {FavouriteService} from "../../../shared/services/favourite.service";
import {GenreService} from "../../../shared/services/genre.service";
import {GenreViewModalService} from "../../../shared/services/genre-view-modal.service";

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent extends BaseComponent implements OnInit {
  @Select(FavouriteState.getFavouriteArtworks)
  private favourites$: Observable<FavouriteArtworkModel[]>;
  favourites: FavouriteArtworkModel[];

  room: RoomModel;
  artworks: ArtworkModel[] = [];
  index: number;
  commentList: any = [];
  favourite: boolean = false;

  public commentForm: FormGroup;
  @ViewChild("inputComment") inputComment: ElementRef;


  constructor(private roomService: RoomService, private artworkService: ArtworkService,
              private route: ActivatedRoute, private artistModalService: ArtistViewModalService,
              private commentService: CommentService, private formBuilder: FormBuilder,
              private store: Store, private genreService: GenreService,
              private genreModalService: GenreViewModalService) {
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
          this.store.dispatch(new GetFavouriteArtworks());
          this.favourites$.pipe(takeUntil(this.unsubscribe$)).subscribe((favourites) => {
            if (favourites) {
              this.favourites = favourites;
              this.favourite = favourites.some(fav => fav.id == this.artworks[this.index].id);
            }
          });
          this.artworks.forEach((artwork, index) => {
            if (artwork.imagePath !== null) {
              artwork.imagePath = ImageUtils.appendImageType(artwork.imagePath);
            }
            this.commentService.getCommentsByArtworkId(artwork.id).pipe(takeUntil(this.unsubscribe$)).subscribe(
              (items) => {
                if (items) {
                  this.commentList.push(items);
                }
              }
            )
          })
        }
      )
  }

  prev() {
    if (this.index < this.artworks.length - 1) {
      this.index++;
      this.favourite = this.favourites.some(fav => fav.id == this.artworks[this.index].id);
    }
  }

  next() {
    if (this.index > 0) {
      this.index--;
      this.favourite = this.favourites.some(fav => fav.id == this.artworks[this.index].id)
    }
  }

  viewArtistModal() {
    this.artworkService.getArtistByArtworkId(this.artworks[this.index].id).pipe(takeUntil(this.unsubscribe$)).subscribe(
      response => {
        this.artistModalService.openModal(response);
      }
    )
  }

  viewGenreModal() {
    this.genreService.getGenreById(this.artworks[this.index].genreId).pipe(takeUntil(this.unsubscribe$)).subscribe(
      response => {
        this.genreModalService.openModal(response);
      }
    )
  }

  reset() {
    this.inputComment.nativeElement.value = '';
  }

  addFavourite(id: number) {
    if (this.favourite) {
      this.favourite = false;
      this.store.dispatch(new RemoveFavouriteArtwork(id));
    } else {
      this.favourite = true;
      this.store.dispatch(new AddFavouriteArtwork(id));
    }
  }

}
