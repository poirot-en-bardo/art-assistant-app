import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../core/components/base/base.component";
import {Select, Store} from "@ngxs/store";
import {UserState} from "../../shared/redux/user/user.state";
import {Observable, takeUntil} from "rxjs";
import {AuthoriseResponseModel} from "../../shared/models/authorise-response.model";
import {ArtworkService} from "../../shared/services/artwork.service";
import {GetLoggedUser} from "../../shared/redux/user/user.action";
import {ArtworkModel} from "../../shared/models/artwork.model";
import {ImageUtils} from "../../shared/utils/image.utils";

@Component({
  selector: 'app-random-art',
  templateUrl: './random-art.component.html',
  styleUrls: ['./random-art.component.scss']
})
export class RandomArtComponent extends BaseComponent implements OnInit {

  artworkIds: number[];
  artwork: ArtworkModel;

  constructor(private artworkService: ArtworkService) {
    super();
  }

  ngOnInit(): void {
    this.getData();
  }

  getData() {
    this.artworkService.getArtworkIds().pipe(takeUntil(this.unsubscribe$)).subscribe(list => {
      this.artworkIds = list;
      let index = this.getRandomId(this.artworkIds.length)
      this.artworkService.getArtworkById(this.artworkIds[index]).pipe(takeUntil(this.unsubscribe$)).subscribe(
        response => {
          this.artwork = response;
          this.artwork.imagePath = ImageUtils.appendImageType(this.artwork.imagePath);
        });
    })
  }

  getRandomId(max: number) {
    return Math.floor(Math.random() * max);
  }

}
