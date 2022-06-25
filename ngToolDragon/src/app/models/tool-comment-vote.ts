import { ToolComment } from './tool-comment';
import { User } from './user';
import { Vote } from './vote';

export class ToolCommentVote {
  user: User | null;
  vote: Vote | null;
  voteDate: Date | null;
  reportedFor: string | null;
  toolComment: ToolComment | null;

  constructor(
    user: User | null = null,
    vote: Vote | null = null,
    voteDate: Date | null = null,
    reportedFor: string | null = '',
    toolComment: ToolComment | null = null
  ) {
    this.user = user;
    this.vote = vote;
    this.voteDate = voteDate;
    this.reportedFor = reportedFor;
    this.toolComment = toolComment;
  }
}
