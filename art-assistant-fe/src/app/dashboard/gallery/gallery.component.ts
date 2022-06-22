import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../core/components/base/base.component";
import {ArtworkService} from "../../shared/services/artwork.service";
import {ActivatedRoute} from "@angular/router";
import {Observable, take, takeUntil} from "rxjs";
import {RoomService} from "../../shared/services/room.service";
import {RoomModel} from "../../shared/models/room.model";
import {GalleryModel} from "../../shared/models/gallery.model";
import {GalleryService} from "../../shared/services/gallery.service";
import {Select, Store} from "@ngxs/store";
import {UserState} from "../../shared/redux/user/user.state";
import {AuthoriseResponseModel} from "../../shared/models/authorise-response.model";
import {GetLoggedUser} from "../../shared/redux/user/user.action";

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent extends BaseComponent implements OnInit {

  @Select(UserState.getLoggedUser)
  private loggedUser$: Observable<AuthoriseResponseModel>;
  loggedUser: AuthoriseResponseModel;

  rooms: RoomModel[];
  gallery: GalleryModel;

  constructor(private store: Store, private galleryService: GalleryService, private roomService: RoomService, private artworkService: ArtworkService,
              private route: ActivatedRoute) {
    super();
  }

  ngOnInit(): void {
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
      this.galleryService.getGalleryById(params['galleryId']).pipe(takeUntil(this.unsubscribe$))
        .subscribe(gallery => {
          this.gallery = gallery;
          this.getRooms();
        })
    })
  }

  getRooms() {
    this.roomService.getRoomsByGalleryId(this.gallery.id).pipe(takeUntil(this.unsubscribe$))
      .subscribe(rooms => {
        this.rooms = rooms;
      })
  }

  editGallery() {

  }

  addRoom() {}
}
