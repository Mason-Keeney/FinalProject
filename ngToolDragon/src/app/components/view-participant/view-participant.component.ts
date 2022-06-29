import { ParticipantService } from './../../services/participant.service';
import { faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AnyCatcher } from 'rxjs/internal/AnyCatcher';
import { Participant } from 'src/app/models/participant';
import { Project } from 'src/app/models/project';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-view-participant',
  templateUrl: './view-participant.component.html',
  styleUrls: ['./view-participant.component.css']
})
export class ViewParticipantComponent implements OnInit {

  //inheritedFields
    @Input() inheritedParticipant: any;
    @Output("reload") reload: EventEmitter<any> = new EventEmitter();

  //fields
    participant: Participant = new Participant();
    project: Project = new Project();
    user: User = new User();
    today: Date = new Date();

  //fa-icons
    faPlusCircle = faPlusCircle;
  constructor(
    private participantService: ParticipantService
  ) { }

  ngOnInit(): void {
    this.participant = this.inheritedParticipant;
    if(this.participant.project){
      this.project = this.participant.project;
    }
    if(this.participant.user){
      this.user = this.participant.user
    }
  }

  approveParticipant(){
    this.participant.dateApproved = this.today;
    this.participant.project = null;
    this.participant.user = null;
    this.participantService.update(this.project.id, this.user.id, this.participant).subscribe({
      next: (result) => {
        this.participant = result;
        this.reload.emit();
      },
      error: (problem) => {
        console.log("ViewParticipantHttpComponent Error: unable to approve Participant");
        console.log(problem);
      }
    })
  }

}
