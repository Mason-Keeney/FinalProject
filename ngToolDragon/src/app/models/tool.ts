import { Category } from './category';
import { ProjectTool } from './project-tool';
import { Status } from './status';
import { ToolComment } from './tool-comment';
import { ToolCondition } from './tool-condition';
import { User } from './user';
export class Tool {
  id: number | null;
  name: string | null;
  description: string | null;
  availability: string | null;
  available: boolean;
  trainingRequired: boolean;
  operators: number | null;
  active: boolean;
  imageUrl: string | null;
  createdAt: Date | null;
  updatedAt: Date | null;
  owner: User | null;
  comments: ToolComment[] | null;
  condition: ToolCondition | null;
  categories: Category[] | null;
  status: Status | null;
  projectUsedIn: ProjectTool[] | null;

  constructor(
    id: number | null = 0,
    name: string | null = '',
    description: string | null = '',
    availability: string | null = '',
    available: boolean = false,
    trainingRequired: boolean = false,
    operators: number | null = 0,
    active: boolean = true,
    imageUrl: string | null = '',
    createdAt: Date | null = null,
    updatedAt: Date | null = null,
    owner: User | null = null,
    comments: ToolComment[] | null = null,
    condition: ToolCondition | null = null,
    categories: Category[] | null = null,
    status: Status | null = null,
    projectUsedIn: ProjectTool[] | null = null

  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.availability = availability;
    this.available = available;
    this.trainingRequired = trainingRequired;
    this.operators = operators;
    this.active = active;
    this.imageUrl = imageUrl;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.owner = owner;
    this.comments = comments;
    this.condition = condition;
    this.categories = categories;
    this.status = status;
    this.projectUsedIn = projectUsedIn;
  }
}
