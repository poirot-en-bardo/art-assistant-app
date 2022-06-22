import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenreViewModalComponent } from './genre-view-modal.component';

describe('GenreViewModalComponent', () => {
  let component: GenreViewModalComponent;
  let fixture: ComponentFixture<GenreViewModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenreViewModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenreViewModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
