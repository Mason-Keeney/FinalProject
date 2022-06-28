import { TestBed } from '@angular/core/testing';

import { ProjectToolService } from './project-tool.service';

describe('ProjectToolService', () => {
  let service: ProjectToolService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjectToolService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
