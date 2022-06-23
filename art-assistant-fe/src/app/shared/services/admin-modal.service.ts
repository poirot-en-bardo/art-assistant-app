import {Injectable} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ModalConstants} from "../constants/modal.constants";
import {MuseumModalComponent} from "../modals/museum-modal/museum-modal.component";

@Injectable()
export class AdminModalService {


  constructor(private modalService: NgbModal) {
  }

  public openModal(object: any, modal: ModalConstants): Promise<any> {
    let modalRef;
    if(modal == ModalConstants.MUSEUM) {
      modalRef = this.modalService.open(MuseumModalComponent);
      modalRef.componentInstance.museum = object;
    }

    // @ts-ignore
    return modalRef.result;
//elsseeee

  }
}
