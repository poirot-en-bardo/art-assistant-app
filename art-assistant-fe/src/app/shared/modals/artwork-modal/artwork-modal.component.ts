import {Component, Input, OnInit} from '@angular/core';
import {FileChangeBaseComponent} from "../../../core/components/base/file-change-base.component";
import {RoomModel} from "../../models/room.model";
import {ArtworkModel} from "../../models/artwork.model";
import {ARTWORK_FORM} from "./artwork-modal.config";
import {ArtistModel} from "../../models/artist.model";
import {GenreModel} from "../../models/genre.model";
import {FormBuilder, Validators} from "@angular/forms";
import {RoomService} from "../../services/room.service";
import {ArtistService} from "../../services/artist.service";
import {GenreService} from "../../services/genre.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {ImageUtils} from "../../utils/image.utils";
import {Store} from "@ngxs/store";
import {ShowSuccessToaster} from "../../redux/toaster/toaster.action";

@Component({
  selector: 'app-artwork-modal',
  templateUrl: './artwork-modal.component.html',
  styleUrls: ['./artwork-modal.component.scss']
})
export class ArtworkModalComponent extends FileChangeBaseComponent implements OnInit {

  @Input() room: RoomModel;
  @Input() artwork: ArtworkModel;

  ARTWORK_FORM = ARTWORK_FORM;
  artists: ArtistModel[];
  genres: GenreModel[];
  rooms: RoomModel[];
  image: ArrayBuffer | string | undefined;

  artworkForm = this.formBuilder.group({
    [ARTWORK_FORM.TITLE]: ['', Validators.required],
    [ARTWORK_FORM.ARTIST]: ['', Validators.required],
    [ARTWORK_FORM.YEAR]: ['', Validators.required],
    [ARTWORK_FORM.GENRE]: ['', Validators.required],
    [ARTWORK_FORM.TECHNIQUE]: ['', Validators.required],
    [ARTWORK_FORM.DESCRIPTION]: ['', Validators.required],
    [ARTWORK_FORM.POSITION]: ['', Validators.required],
    [ARTWORK_FORM.ROOM]: ['', Validators.required],
    [ARTWORK_FORM.IMAGE]: ['']
  });

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder,
              private roomService: RoomService, private artistService: ArtistService,
              private genreService: GenreService, private store: Store) {
    super();
  }

  ngOnInit(): void {
    this.artistService.getArtists().subscribe(response => this.artists = response);
    this.genreService.getAllGenres().subscribe(response => this.genres = response);

    if (this.artwork) {
      this.roomService.getRoomById(this.artwork.roomId).subscribe(
        response => {
          this.roomService.getRoomsByGalleryId(response.galleryId).subscribe(
            roomList => this.rooms = roomList
          )
        }
      )
    }
  }

  close() {
    this.activeModal.close(null);
  }

  addArtwork() {
    if (this.file) {
      this.image = ImageUtils.extractImage(this.file);
    } else if (this.artwork?.imagePath) {
      this.image = ImageUtils.extractImage(this.artwork?.imagePath);
    }
    const newArtwork = this.artworkForm.getRawValue();
    let result = {
      title: newArtwork.title,
      artistId: newArtwork.artist,
      year: newArtwork.year,
      genreId: newArtwork.genre,
      technique: newArtwork.technique,
      description: newArtwork.description,
      position: newArtwork.position,
      roomId: 0,
      imagePath: this.image
    }
    if(this.room) {
      result.roomId = this.room.id;
    } else {
      result.roomId = newArtwork.room;
    }
    this.activeModal.close(result);
    this.store.dispatch(new ShowSuccessToaster());
  }
}
