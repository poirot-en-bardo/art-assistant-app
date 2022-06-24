import {Component, Input, OnInit} from '@angular/core';
import {BaseComponent} from "../../../core/components/base/base.component";
import {GalleryModel} from "../../models/gallery.model";
import {MuseumModel} from "../../models/museum.model";
import {RoomModel} from "../../models/room.model";
import {ROOM_FORM} from "./room-modal.config";
import {FormBuilder, Validators} from "@angular/forms";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Store} from "@ngxs/store";
import {GalleryService} from "../../services/gallery.service";
import {takeUntil} from "rxjs";
import {ShowSuccessToaster} from "../../redux/toaster/toaster.action";
import {FileChangeBaseComponent} from "../../../core/components/base/file-change-base.component";
import {ImageUtils} from "../../utils/image.utils";

@Component({
  selector: 'app-room-modal',
  templateUrl: './room-modal.component.html',
  styleUrls: ['./room-modal.component.scss']
})
export class RoomModalComponent extends FileChangeBaseComponent implements OnInit {

  @Input() gallery: GalleryModel;
  @Input() room: RoomModel;

  ROOM_FORM = ROOM_FORM;
  galleries: GalleryModel[];
  image: ArrayBuffer | string | undefined;

  roomForm = this.formBuilder.group({
    [ROOM_FORM.GALLERY]: ['', Validators.required],
    [ROOM_FORM.NUMBER]: ['', Validators.required],
    [ROOM_FORM.FLOOR]: ['', Validators.required],
    [ROOM_FORM.MAP]: ['']
  });

  constructor(private formBuilder: FormBuilder, public activeModal: NgbActiveModal,
              private store: Store, private galleryService: GalleryService) {
    super();
  }

  ngOnInit(): void {
    if (this.room) {
      this.galleryService.getGalleryById(this.room.galleryId).subscribe(
        response => {
          this.galleryService.getGalleriesByMuseumId(response.museumId).subscribe(
            galleryList => this.galleries = galleryList
          )
        }
      )
    }
  }

  close() {
    this.activeModal.close(null);
  }

  addRoom() {
    if (this.file) {
      this.image = ImageUtils.extractImage(this.file);
    } else if (this.room?.map) {
      this.image = ImageUtils.extractImage(this.room?.map);
    }
    const newRoom = this.roomForm.getRawValue();
    let result = {
      galleryId: 0,
      number: newRoom.number,
      floor: newRoom.floor,
      map: this.image
    }
    if (this.gallery) {
      result.galleryId = this.gallery.id;
    } else {
      result.galleryId = newRoom.gallery;
    }
    this.activeModal.close(result);
    this.store.dispatch(new ShowSuccessToaster());
  }

  deleteRoom() {

  }


}
