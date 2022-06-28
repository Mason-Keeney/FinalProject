import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-inspect-project',
  templateUrl: './inspect-project.component.html',
  styleUrls: ['./inspect-project.component.css']
})
export class InspectProjectComponent implements OnInit {

  @Input() project: any;

  constructor() { }

  ngOnInit(): void {
  }

}
