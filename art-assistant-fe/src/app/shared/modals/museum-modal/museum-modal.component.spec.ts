import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MuseumModalComponent } from './museum-modal.component';

describe('MuseumModalComponent', () => {
  let component: MuseumModalComponent;
  let fixture: ComponentFixture<MuseumModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MuseumModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MuseumModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
