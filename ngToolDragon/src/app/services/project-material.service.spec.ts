import { TestBed } from '@angular/core/testing';

import { ProjectMaterialService } from './project-material.service';

describe('ProjectMaterialService', () => {
  let service: ProjectMaterialService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjectMaterialService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
