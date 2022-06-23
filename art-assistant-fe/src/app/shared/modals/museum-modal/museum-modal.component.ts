import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {ArtistModel} from "../../models/artist.model";
import {MuseumModel} from "../../models/museum.model";
import {FormBuilder, Validators} from "@angular/forms";
import {MUSEUM_FORM} from "./museum-modal.config";
import {BaseComponent} from "../../../core/components/base/base.component";
import {CountryService} from "../../services/country.service";
import {CountryModel} from "../../models/country.model";
import {takeUntil} from "rxjs";
import {Store} from "@ngxs/store";
import {ShowSuccessToaster} from "../../redux/toaster/toaster.action";

@Component({
  selector: 'app-museum-modal',
  templateUrl: './museum-modal.component.html',
  styleUrls: ['./museum-modal.component.scss']
})
export class MuseumModalComponent extends BaseComponent implements OnInit {

  @Input() museum: MuseumModel;

  MUSEUM_FORM=MUSEUM_FORM;
  countries: CountryModel[];


  museumForm = this.formBuilder.group({
    [MUSEUM_FORM.NAME]: ['', Validators.required],
    [MUSEUM_FORM.ADDRESS]: ['', Validators.required],
    [MUSEUM_FORM.COUNTRY]: ['', Validators.required],
  });


  constructor(public activeModal: NgbActiveModal, private formBuilder: FormBuilder,
              private countryService: CountryService, private store: Store) {
    super();
  }

  ngOnInit(): void {
    this.countryService.getCountries().pipe(takeUntil(this.unsubscribe$)).subscribe((response) => {
      this.countries = response;
    })
  }

  close() {
    this.activeModal.close(null);
  }

  saveMuseum() {
    const newMuseum = this.museumForm.getRawValue();
    this.activeModal.close({
      name: newMuseum.name,
      address: newMuseum.address,
      countryId: newMuseum.country
    })
    this.store.dispatch(new ShowSuccessToaster());
  }
}
