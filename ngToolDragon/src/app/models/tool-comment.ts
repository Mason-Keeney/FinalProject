import { ToolCommentVote } from './tool-comment-vote';
import { Vote } from './vote';
import { Tool } from './tool';
import { User } from './user';
export class ToolComment {
  id: number | null;
  commentBody: string | null;
  createdDate: Date | null;
  active: boolean;
  user: User | null;
  tool: Tool | null;
  votes: ToolCommentVote[] | null;

  constructor(
    id: number | null = 0,
    commentBody: string | null = '',
    createdDate: Date | null = null,
    active: boolean = true,
    user: User | null = null,
    tool: Tool | null = null,
    votes: ToolCommentVote[] | null = null
  ) {
    this.id = id;
    this.commentBody = commentBody;
    this.createdDate = createdDate;
    this.active = active;
    this.user = user;
    this.tool = tool;
    this.votes = votes;
  }
}
