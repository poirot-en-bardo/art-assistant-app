import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MuseumPageComponent } from './museum-page.component';

describe('MuseumPageComponent', () => {
  let component: MuseumPageComponent;
  let fixture: ComponentFixture<MuseumPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MuseumPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MuseumPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
