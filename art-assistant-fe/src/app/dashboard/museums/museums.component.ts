import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../core/components/base/base.component";
import {MuseumService} from "../../shared/services/museum.service";
import {Observable, takeUntil} from "rxjs";
import {MuseumModel} from "../../shared/models/museum.model";
import {Select} from "@ngxs/store";
import {UserState} from "../../shared/redux/user/user.state";
import {AuthoriseResponseModel} from "../../shared/models/authorise-response.model";

@Component({
  selector: 'app-museums',
  templateUrl: './museums.component.html',
  styleUrls: ['./museums.component.scss']
})
export class MuseumsComponent extends BaseComponent implements OnInit {

  // @Select(UserState.getLoggedUser)
  // private loggedUser$: Observable<AuthoriseResponseModel>;
  // loggedUser: AuthoriseResponseModel;


  museums: MuseumModel[];

  constructor(private museumService: MuseumService) {
    super();
  }

  ngOnInit(): void {
    this.getMuseums();
  }

  getMuseums() {
    this.museumService.getMuseums().pipe(takeUntil(this.unsubscribe$))
      .subscribe((museums) => {
        this.museums = museums;
        console.log(museums);
      }
    )
  }
}
