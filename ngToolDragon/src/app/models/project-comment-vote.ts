import { ProjectComment } from './project-comment';
import { User } from './user';
import { Vote } from './vote';

export class ProjectCommentVote {
  user: User | null;
  vote: Vote | null;
  voteDate: Date | null;
  reportedFor: string | null;
  projectComment: ProjectComment | null;

  constructor(
    user: User | null = null,
    vote: Vote | null = null,
    voteDate: Date | null = null,
    reportedFor: string | null = '',
    projectComment: ProjectComment | null = null
  ) {
    this.user = user;
    this.vote = vote;
    this.voteDate = voteDate;
    this.reportedFor = reportedFor;
    this.projectComment = projectComment;
  }
}
