import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-museum-page',
  templateUrl: './museum-page.component.html',
  styleUrls: ['./museum-page.component.scss']
})
export class MuseumPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log("here");
  }

}
