import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectToolCreateComponent } from './project-tool-create.component';

describe('ProjectToolCreateComponent', () => {
  let component: ProjectToolCreateComponent;
  let fixture: ComponentFixture<ProjectToolCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectToolCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectToolCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
