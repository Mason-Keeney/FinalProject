import { ProjectCommentVote } from './project-comment-vote';
import { Project } from './project';
import { User } from './user';
export class ProjectComment {
  id: number | null;
  commentBody: string | null;
  createdDate: Date | null;
  active: boolean;
  user: User | null;
  project: Project | null;
  votes: ProjectCommentVote | null;

  constructor(
    id: number | null = 0,
    commentBody: string | null = '',
    createdDate: Date | null = null,
    active: boolean = true,
    user: User | null = null,
    project: Project | null = null,
    votes: ProjectCommentVote | null = null
  ) {
    this.id = id;
    this.commentBody = commentBody;
    this.createdDate = createdDate;
    this.active = active;
    this.user = user;
    this.project = project;
    this.votes = votes;
  }
}
