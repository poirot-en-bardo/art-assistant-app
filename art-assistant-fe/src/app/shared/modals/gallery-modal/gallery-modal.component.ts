import {Component, Input, OnInit} from '@angular/core';
import {BaseComponent} from "../../../core/components/base/base.component";
import {GalleryModel} from "../../models/gallery.model";
import {GALLERY_FORM} from "./gallery-modal.config";
import {MuseumModel} from "../../models/museum.model";
import {FormBuilder, Validators} from "@angular/forms";
import {MuseumService} from "../../services/museum.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Store} from "@ngxs/store";
import {takeUntil} from "rxjs";
import {ShowSuccessToaster} from "../../redux/toaster/toaster.action";

@Component({
  selector: 'app-gallery-modal',
  templateUrl: './gallery-modal.component.html',
  styleUrls: ['./gallery-modal.component.scss']
})
export class GalleryModalComponent extends BaseComponent implements OnInit {

  @Input() gallery: GalleryModel;
  @Input() museum: MuseumModel;

  GALLERY_FORM = GALLERY_FORM;
  museums: MuseumModel[];

  galleryForm = this.formBuilder.group({
    [GALLERY_FORM.NAME]: ['', [Validators.required, Validators.maxLength(200)]],
    [GALLERY_FORM.MUSEUM]: ['', Validators.required]
  });

  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder,
              private store: Store, private museumService: MuseumService) {
    super();
  }

  ngOnInit(): void {
    if (this.gallery) {
      this.museumService.getMuseums().pipe(takeUntil(this.unsubscribe$)).subscribe(
        response => this.museums = response
      )
    }
  }

  close() {
    this.activeModal.close(null);
  }

  addGallery() {
    const newGallery = this.galleryForm.getRawValue();
    let result = {
      name: newGallery.name,
      museumId: 0
    }
    if (this.museum) {
      result.museumId = this.museum.id;
    } else {
      result.museumId = newGallery.museum;
    }
    this.activeModal.close(result);
    this.store.dispatch(new ShowSuccessToaster());
  }

  deleteGallery() {
  }

}
