import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputMultFilesComponent } from './input-mult-files.component';

describe('InputMultFilesComponent', () => {
  let component: InputMultFilesComponent;
  let fixture: ComponentFixture<InputMultFilesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InputMultFilesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InputMultFilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
