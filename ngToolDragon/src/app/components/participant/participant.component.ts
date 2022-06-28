import { ParticipantService } from './../../services/participant.service';
import { Participant } from './../../models/participant';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.css']
})
export class ParticipantComponent implements OnInit {

  pid: number | null = null;


  @Input() participant: any;
  newParticipant:Participant = new Participant;

  constructor(
    private partServe: ParticipantService
  ) { }

  ngOnInit(): void {
    this.stripParticipate();

    console.log(this.pid)
    // console.log(this.participant)
  }

  stripParticipate() {
    if (this.participant){
      console.log(this.participant);
      if (this.participant.project){
       this.pid = this.participant.project.id;
       this.participant.project = null;
       this.participant.user = null;
      }
    }
  }

  createParticipant(participant: Participant): void {


    this.partServe.create(participant, this.pid).subscribe({
      next: (result) => {
        window.alert('Your request has been sent!');
      },
      error: (nojoy) => {
        console.error(
          'participant.create(): error creating participant:'
        );
        console.error(nojoy);
      },
    })
  }

}
