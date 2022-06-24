import {Injectable} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ModalConstants} from "../constants/modal.constants";
import {MuseumModalComponent} from "../modals/museum-modal/museum-modal.component";
import {GalleryModalComponent} from "../modals/gallery-modal/gallery-modal.component";
import {RoomModalComponent} from "../modals/room-modal/room-modal.component";
import {ArtworkModalComponent} from "../modals/artwork-modal/artwork-modal.component";

@Injectable()
export class AdminModalService {


  constructor(private modalService: NgbModal) {
  }

  public openModal(object: any, extra: any, modal: ModalConstants): Promise<any> {
    let modalRef;
    switch (modal) {
      case ModalConstants.MUSEUM: {
        modalRef = this.modalService.open(MuseumModalComponent);
        modalRef.componentInstance.museum = object;
        break;
      }
      case ModalConstants.GALLERY: {
        modalRef = this.modalService.open(GalleryModalComponent);
        modalRef.componentInstance.gallery = object;
        modalRef.componentInstance.museum = extra;
        break;
      }
      case ModalConstants.ROOM: {
        modalRef = this.modalService.open(RoomModalComponent);
        modalRef.componentInstance.room = object;
        modalRef.componentInstance.gallery = extra;
        break;
      }
      case ModalConstants.ARTWORK: {
        modalRef = this.modalService.open(ArtworkModalComponent);
        modalRef.componentInstance.artwork = object;
        modalRef.componentInstance.room = extra;
        break;
      }
    }

    // @ts-ignore
    return modalRef.result;
  }
}
