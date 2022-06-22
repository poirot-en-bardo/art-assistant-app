import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {ArtistModel} from "../../models/artist.model";
import {MuseumModel} from "../../models/museum.model";
import {FormBuilder, Validators} from "@angular/forms";
import {MUSEUM_FORM} from "./museum-modal.config";

@Component({
  selector: 'app-museum-modal',
  templateUrl: './museum-modal.component.html',
  styleUrls: ['./museum-modal.component.scss']
})
export class MuseumModalComponent implements OnInit {

  @Input() museum: MuseumModel;

  MUSEUM_FORM=MUSEUM_FORM;


  museumForm = this.formBuilder.group({
    [MUSEUM_FORM.NAME]: ['', Validators.required],
    [MUSEUM_FORM.ADDRESS]: ['', Validators.required],
    [MUSEUM_FORM.COUNTRY]: ['', Validators.required],
  });


  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  close() {
    this.activeModal.close(null);
  }
}
