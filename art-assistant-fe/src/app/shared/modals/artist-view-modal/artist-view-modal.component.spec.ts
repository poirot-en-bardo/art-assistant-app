import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistViewModalComponent } from './artist-view-modal.component';

describe('ArtistViewModalComponent', () => {
  let component: ArtistViewModalComponent;
  let fixture: ComponentFixture<ArtistViewModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArtistViewModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtistViewModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
