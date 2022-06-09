import {Component, Input, OnInit} from '@angular/core';
import {BaseComponent} from "../../../core/components/base/base.component";
import {GalleryModel} from "../../../shared/models/gallery.model";
import {GalleryService} from "../../../shared/services/gallery.service";
import {takeUntil} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {MuseumModel} from "../../../shared/models/museum.model";
import {MuseumService} from "../../../shared/services/museum.service";

@Component({
  selector: 'app-museum-page',
  templateUrl: './museum-page.component.html',
  styleUrls: ['./museum-page.component.scss']
})
export class MuseumPageComponent extends BaseComponent implements OnInit {

  museum: MuseumModel;
  galleries: GalleryModel[];
  searchText = "";

  constructor(private galleryService: GalleryService, private museumService: MuseumService,
              private route: ActivatedRoute, private router: Router) {
    super();
  }

  ngOnInit(): void {
    this.getData();
  }

  getData() {
    this.route.params.pipe(takeUntil(this.unsubscribe$)).subscribe(params => {
      this.museumService.getMuseumById(params['museumId']).pipe(takeUntil(this.unsubscribe$))
        .subscribe(museum => {
          this.museum = museum;
          console.log(museum);
          this.getGalleries();
        });
    })

  }
  getGalleries() {
    this.galleryService.getGalleriesByMuseumId(this.museum.id).pipe(takeUntil(this.unsubscribe$))
      .subscribe(galleries => {
        console.log(galleries);
        this.galleries = galleries;
      });
  }

}
