import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {BaseComponent} from "../../../core/components/base/base.component";
import {RoomService} from "../../../shared/services/room.service";
import {ArtworkService} from "../../../shared/services/artwork.service";
import {ActivatedRoute} from "@angular/router";
import {RoomModel} from "../../../shared/models/room.model";
import {ArtworkModel} from "../../../shared/models/artwork.model";
import {Observable, takeUntil} from "rxjs";
import {ImageUtils} from '../../../shared/utils/image.utils';
import {ArtistViewModalService} from "../../../shared/services/artist-view-modal.service";
import {CommentService} from "./comment.service";
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
import {GenreService} from "../../../shared/services/genre.service";
import {GenreViewModalService} from "../../../shared/services/genre-view-modal.service";
import {AdminModalService} from "../../../shared/services/admin-modal.service";
import {ModalConstants} from "../../../shared/constants/modal.constants";

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent extends BaseComponent implements OnInit {
  @Select(FavouriteState.getFavouriteArtworks)
  private favourites$: Observable<FavouriteArtworkModel[]>;
  favourites: FavouriteArtworkModel[];

  @Select(UserState.getLoggedUser)
  private loggedUser$: Observable<AuthoriseResponseModel>;
  loggedUser: AuthoriseResponseModel;

  @Input() roomIndex:number;

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
              private genreModalService: GenreViewModalService,
              public modalService: AdminModalService) {
    super();
  }

  ngOnInit(): void {
    this.index = 0;
    this.commentForm = this.formBuilder.group({
        [CommentForm.MESSAGE]: ['', Validators.required]
      }
    )
    this.store.dispatch(new GetLoggedUser());
    this.loggedUser$.pipe(takeUntil(this.unsubscribe$)).subscribe((userModel) => {
      if (userModel) {
        this.loggedUser = userModel;
      }
    });
    this.getData();
  }

  getData() {
    this.route.params.pipe(takeUntil(this.unsubscribe$)).subscribe(params => {
      this.roomService.getRoomById(params['roomId']).pipe(takeUntil(this.unsubscribe$))
        .subscribe(room => {
          if(room.map !== null) {
            room.map = ImageUtils.appendImageType(room.map);
          }
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
              this.favourite = favourites.some(fav => fav.id == this.artworks[this.index]?.id);
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

  editRoom() {
      this.modalService.openModal(this.room, null, ModalConstants.ROOM).then((newRoom) => {
        if(newRoom) {
          this.roomService.updateRoom(newRoom, this.room.id).pipe(takeUntil(this.unsubscribe$)).subscribe(
            response => {
              if(response.map !== null){
                response.map = ImageUtils.appendImageType(response.map);
              }
              this.room = response
            }
          )
        }
      })
  }

  addArtwork() {
      this.modalService.openModal(null, this.room, ModalConstants.ARTWORK).then(newArtwork => {
        if(newArtwork) {
          this.artworkService.addArtwork(newArtwork).pipe(takeUntil(this.unsubscribe$)).subscribe(
            response => window.location.reload()
          )
        }
      })
  }

  editArtwork() {
      this.modalService.openModal(this.artworks[this.index], null, ModalConstants.ARTWORK).then(newArtwork => {
        if(newArtwork) {
          this.artworkService.updateArtwork(newArtwork, this.artworks[this.index].id).pipe(takeUntil(this.unsubscribe$))
            .subscribe(response => {
              if(response.imagePath !== null) {
                response.imagePath = ImageUtils.appendImageType(response.imagePath);
              }
              this.artworks[this.index] = response;
            })
        }
      })
  }

  deleteArtwork(id: number) {
    if (confirm('Are you sure you want to remove the artwork?')) {
      this.artworkService.deleteArtworkById(id).pipe(takeUntil(this.unsubscribe$)).subscribe(() => {
          this.artworks = this.artworks.filter((item) => {
            return item.id !== id;
          })
        }
      )
    }
  }

}
