import { Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {GenreModel} from "../models/genre.model";
import {GenreViewModalComponent} from "../modals/genre-view-modal/genre-view-modal.component";

@Injectable()
export class GenreViewModalService {

  genre: GenreModel;

  constructor(private modalService: NgbModal) {
  }

  public openModal(genre: GenreModel): void {
    const modalRef = this.modalService.open(GenreViewModalComponent);
    modalRef.componentInstance.genre = genre;
  }
}
