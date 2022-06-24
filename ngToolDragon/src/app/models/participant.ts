import { User } from './user';
import { Project } from './project';
export class Participant {
  project: Project | null;
  user: User | null;
  participantComment: string | null;
  dateCreated: Date | null;
  dateApproved: Date | null;
  rating: number | null;
  ratingComment: string | null;
  ratingDate: Date | null;

  constructor(
    project: Project | null = null,
    user: User | null = null,
    participantComment: string | null = '',
    dateCreated: Date | null = null,
    dateApproved: Date | null = null,
    rating: number | null = 0,
    ratingComment: string | null = '',
    ratingDate: Date | null = null
  ) {
    this.project = project;
    this.user = user;
    this.participantComment = participantComment;
    this.dateCreated = dateCreated;
    this.dateApproved = dateApproved;
    this.rating = rating;
    this.ratingComment = ratingComment;
    this.ratingDate = ratingDate;
  }
}
