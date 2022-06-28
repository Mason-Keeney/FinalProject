import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InspectProjectComponent } from './inspect-project.component';

describe('InspectProjectComponent', () => {
  let component: InspectProjectComponent;
  let fixture: ComponentFixture<InspectProjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InspectProjectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InspectProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
