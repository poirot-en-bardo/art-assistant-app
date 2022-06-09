import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomArtComponent } from './random-art.component';

describe('RandomArtworkComponent', () => {
  let component: RandomArtComponent;
  let fixture: ComponentFixture<RandomArtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RandomArtComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomArtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
