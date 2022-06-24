import { Material } from './material';
import { Project } from './project';
import { Status } from './status';
export class ProjectMaterial {
  id: number | null;
  project: Project | null;
  material: Material | null;
  status: Status | null;
  quantity: string | null;

  constructor(
    id: number | null = 0,
    project: Project | null = null,
    material: Material | null = null,
    status: Status | null = null,
    quantity: string | null = ''
  ) {
    this.id = id;
    this.project = project;
    this.material = material;
    this.status = status;
    this.quantity = quantity;
  }
}
