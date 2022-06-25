import { Project } from './project';
import { ProjectComment } from './project-comment';
import { Tool } from './tool';
export class ProjectTool {
  tool: Tool | null;
  project: Project | null;
  projectComment: ProjectComment | null;
  dateCreated: Date | null;
  dateApproved: Date | null;
  projectOwnerRating: number | null;
  toolOwnerRating: number | null;
  projectOwnerRatingDate: Date | null;
  toolOwnerRatingDate: Date | null;
  projectOwnerRatingComment: string | null;
  toolOwnerRatingComment: string | null;

  constructor(
    tool: Tool | null = null,
    project: Project | null = null,
    projectComment: ProjectComment | null = null,
    dateCreated: Date | null = null,
    dateApproved: Date | null = null,
    projectOwnerRating: number | null = 0,
    toolOwnerRating: number | null = 0,
    projectOwnerRatingDate: Date | null = null,
    toolOwnerRatingDate: Date | null = null,
    projectOwnerRatingComment: string | null = '',
    toolOwnerRatingComment: string | null = ''
  ) {
    this.tool = tool;
    this.project = project;
    this.projectComment = projectComment;
    this.dateCreated = dateCreated;
    this.dateApproved = dateApproved;
    this.projectOwnerRating = projectOwnerRating;
    this.toolOwnerRating = toolOwnerRating;
    this.projectOwnerRatingDate = projectOwnerRatingDate;
    this.toolOwnerRatingDate = toolOwnerRatingDate;
    this.projectOwnerRatingComment = projectOwnerRatingComment;
    this.toolOwnerRatingComment = toolOwnerRatingComment;
  }
}
