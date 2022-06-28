import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectToolComponent } from './project-tool.component';

describe('ProjectToolComponent', () => {
  let component: ProjectToolComponent;
  let fixture: ComponentFixture<ProjectToolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectToolComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectToolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
