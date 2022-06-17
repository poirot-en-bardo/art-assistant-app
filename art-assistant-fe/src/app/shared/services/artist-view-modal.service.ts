import { Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {ArtistModel} from "../models/artist.model";
import {ArtistViewModalComponent} from "../modals/artist-view-modal/artist-view-modal.component";

@Injectable()
export class ArtistViewModalService {

  artist: ArtistModel;

  constructor(private modalService: NgbModal) {
  }

  public openModal(artist: ArtistModel): void {
    const modalRef = this.modalService.open(ArtistViewModalComponent);
    modalRef.componentInstance.artist = artist;
  }
}
