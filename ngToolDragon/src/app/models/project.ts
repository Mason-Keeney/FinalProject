import { ProjectMaterial } from './project-material';
import { ProjectComment } from './project-comment';
import { Category } from './category';
import { ProjectTool } from './project-tool';
import { Address } from './address';
import { Participant } from './participant';
import { User } from './user';
export class Project {

  id: number | null;
  description: string | null;
  startDate: Date | null;
  updatedAt: Date | null;
  estimatedEndDate: Date | null;
  completed: boolean;
  cancelled: boolean;
  active: boolean;
  imageUrl: string | null;
  createdAt: Date | null;
  owner: User | null;
  particpants: Participant[] | null;
  address: Address|null;
  projectTools: ProjectTool[] | null;
  catergories: Category[] | null;
  comments: ProjectComment[] | null;
  projectMaterials: ProjectMaterial[] | null;

constructor(
  id: number | null = 0,
  description: string | null = '',
  startDate: Date | null = null,
  updatedAt: Date | null = null,
  estimatedEndDate: Date | null = null,
  completed: boolean =false,
  cancelled: boolean =false,
  active: boolean =true,
  imageUrl: string | null ='',
  createdAt: Date | null =null,
  owner: User | null =null,
  particpants: Participant[] | null =null,
  address: Address|null = null,
  projectTools: ProjectTool[] | null = null,
  catergories: Category[] | null= null,
  comments: ProjectComment[] | null= null,
  projectMaterials: ProjectMaterial[] | null= null

) {
this.id = id;
this.description = description;
this.startDate = startDate;
this.updatedAt = updatedAt;
this.estimatedEndDate = estimatedEndDate;
this.completed = completed;
this.cancelled = cancelled;
this.active = active;
this.imageUrl = imageUrl;
this.createdAt = createdAt;
this.owner = owner;
this.particpants = particpants;
this.address = address;
this.projectTools = projectTools;
this.catergories = catergories;
this.comments = comments;
this.projectMaterials = projectMaterials;

}





}
