import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {GenreModel} from "../../models/genre.model";

@Component({
  selector: 'app-genre-view-modal',
  templateUrl: './genre-view-modal.component.html',
  styleUrls: ['./genre-view-modal.component.scss']
})
export class GenreViewModalComponent implements OnInit {

  @Input() genre: GenreModel;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  close() {
    this.activeModal.close(null);
  }

}
