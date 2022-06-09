import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../core/components/base/base.component";
import {MuseumService} from "../../shared/services/museum.service";
import {takeUntil} from "rxjs";
import {MuseumModel} from "../../shared/models/museum.model";

@Component({
  selector: 'app-museums',
  templateUrl: './museums.component.html',
  styleUrls: ['./museums.component.scss']
})
export class MuseumsComponent extends BaseComponent implements OnInit {


  museums: MuseumModel[];
  searchText = "";

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
      }
    )
  }

  search(){
    if(this.searchText!== ""){
      let searchValue = this.searchText.toLocaleLowerCase();

      this.museums = this.museums.filter((museum:any) =>{
        if(museum.name.toLocaleLowerCase().match(searchValue) ||
          museum.countryName.toLocaleLowerCase().match(searchValue))
        return museum;
      });
    }
    else {
      this.getMuseums();
      }
    }

}
