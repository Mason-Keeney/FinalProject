import { Participant } from './../../models/participant';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.css']
})
export class ParticipantComponent implements OnInit {

  @Input() participant: any;

  constructor() { }

  ngOnInit(): void {
  }

}
