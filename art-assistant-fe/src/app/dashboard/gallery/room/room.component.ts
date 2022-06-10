import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../../core/components/base/base.component";
import {RoomService} from "../../../shared/services/room.service";
import {ArtworkService} from "../../../shared/services/artwork.service";
import {ActivatedRoute} from "@angular/router";
import {RoomModel} from "../../../shared/models/room.model";
import {ArtworkModel} from "../../../shared/models/artwork.model";
import {takeUntil} from "rxjs";
import {ImageUtils} from '../../../shared/utils/image.utils';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent extends BaseComponent implements OnInit {

  room: RoomModel;
  artworks: ArtworkModel[];
  index: number;

  constructor(private roomService: RoomService, private artworkService: ArtworkService,
              private route: ActivatedRoute) {
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
        this.artworks.forEach(artwork => {
            if (artwork.imagePath !== null)
              artwork.imagePath = ImageUtils.appendImageType(artwork.imagePath);
          }
        )
        console.log(artworks);
      })
  }

  prev() {
    if (this.index < this.artworks.length - 1) {
      this.index++;
    }
    console.log(this.index);
  }

  next() {
    if (this.index > 0) {
      this.index--;
    }
    console.log(this.index);

  }

}
