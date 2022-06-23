import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../../core/components/base/base.component";
import {GalleryModel} from "../../../shared/models/gallery.model";
import {GalleryService} from "../../../shared/services/gallery.service";
import {Observable, takeUntil} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {MuseumModel} from "../../../shared/models/museum.model";
import {MuseumService} from "../../../shared/services/museum.service";
import {Select, Store} from "@ngxs/store";
import {UserState} from "../../../shared/redux/user/user.state";
import {AuthoriseResponseModel} from "../../../shared/models/authorise-response.model";
import {GetLoggedUser} from "../../../shared/redux/user/user.action";
import {AdminModalService} from "../../../shared/services/admin-modal.service";
import {ModalConstants} from "../../../shared/constants/modal.constants";

@Component({
  selector: 'app-museum-page',
  templateUrl: './museum-page.component.html',
  styleUrls: ['./museum-page.component.scss']
})
export class MuseumPageComponent extends BaseComponent implements OnInit {

  @Select(UserState.getLoggedUser)
  private loggedUser$: Observable<AuthoriseResponseModel>;
  loggedUser: AuthoriseResponseModel;

  museum: MuseumModel;
  galleries: GalleryModel[];
  searchText = "";

  constructor(private galleryService: GalleryService, private museumService: MuseumService,
              private route: ActivatedRoute, private store: Store, private router: Router,
              private modalService: AdminModalService) {
    super();
  }

  ngOnInit(): void {
    this.store.dispatch(new GetLoggedUser());
    this.loggedUser$.pipe(takeUntil(this.unsubscribe$)).subscribe((userModel) => {
      if (userModel) {
        this.loggedUser = userModel;
      }
    });
    this.getData();
  }

  getData() {
    this.route.params.pipe(takeUntil(this.unsubscribe$)).subscribe(params => {
      this.museumService.getMuseumById(params['museumId']).pipe(takeUntil(this.unsubscribe$))
        .subscribe(museum => {
          this.museum = museum;
          this.getGalleries();
        });
    })

  }
  getGalleries() {
    this.galleryService.getGalleriesByMuseumId(this.museum.id).pipe(takeUntil(this.unsubscribe$))
      .subscribe(galleries => {
        this.galleries = galleries;
      });
  }

  search(){
    if(this.searchText!== ""){
      let searchValue = this.searchText.toLocaleLowerCase();

      this.galleries = this.galleries.filter((gallery:any) =>{
        if(gallery.name.toLocaleLowerCase().match(searchValue))
          return gallery;
      });
    }
    else {
      this.getGalleries();
    }
  }

  openMaps(){
    let address = this.museum.address + this.museum.countryName;
    let url = "https://www.google.com.sa/maps/search/"+ encodeURI(address);
    window.open(url, '_blank');
  }

  goToGallery(galleryId: number) {
    if(!this.loggedUser) {
      alert("Please log in to access the Gallery Page");
    }
    // ['../../gallery', gallery.id]
    this.router.navigate([`../../gallery/${galleryId}`]);

  }

  editMuseum() {
    this.modalService.openModal(this.museum, null, ModalConstants.MUSEUM).then((newMuseum) => {
        this.museumService.updateMuseum(newMuseum, this.museum.id).pipe(takeUntil(this.unsubscribe$)).subscribe(
          response => this.museum = response
        )
    });
  }

  addGallery() {
    this.modalService.openModal(null, this.museum, ModalConstants.GALLERY).then((newGallery) => {
      console.log(newGallery);
      this.galleryService.addGallery(newGallery).pipe(takeUntil(this.unsubscribe$)).subscribe(
        response => this.galleries.unshift(response)
      )
    })
  }
}
