import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../core/components/base/base.component";
import {ArtworkService} from "../../shared/services/artwork.service";
import {ActivatedRoute} from "@angular/router";
import {take, takeUntil} from "rxjs";
import {RoomService} from "../../shared/services/room.service";
import {RoomModel} from "../../shared/models/room.model";
import {GalleryModel} from "../../shared/models/gallery.model";
import {GalleryService} from "../../shared/services/gallery.service";

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent extends BaseComponent implements OnInit {

  rooms: RoomModel[];
  gallery: GalleryModel;

  constructor(private galleryService: GalleryService, private roomService: RoomService, private artworkService: ArtworkService,
              private route: ActivatedRoute) {
    super();
  }

  ngOnInit(): void {
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
}
