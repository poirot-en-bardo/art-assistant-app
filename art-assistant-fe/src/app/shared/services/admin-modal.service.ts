import {Injectable} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ModalConstants} from "../constants/modal.constants";
import {MuseumModalComponent} from "../modals/museum-modal/museum-modal.component";

@Injectable()
export class AdminModalService {


  constructor(private modalService: NgbModal) {
  }

  public openModal(object: any, modal: ModalConstants): void {
    if(modal == ModalConstants.MUSEUM) {
      const modalRef = this.modalService.open(MuseumModalComponent);
      modalRef.componentInstance.museum = object;
    }
//elsseeee

  }
}
