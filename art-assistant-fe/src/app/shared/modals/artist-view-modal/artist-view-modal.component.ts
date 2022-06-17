import {Component, Input, OnInit} from '@angular/core';
import {ArtistModel} from "../../models/artist.model";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-artist-view-modal',
  templateUrl: './artist-view-modal.component.html',
  styleUrls: ['./artist-view-modal.component.scss']
})
export class ArtistViewModalComponent implements OnInit {

  @Input() artist: ArtistModel;

  constructor(public activeModal: NgbActiveModal) {
  }

  ngOnInit(): void {
  }

  close() {
    this.activeModal.close(null);
  }

}
