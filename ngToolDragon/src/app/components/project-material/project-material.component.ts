import { faFloppyDisk } from '@fortawesome/free-solid-svg-icons';
import { ProjectMaterial } from './../../models/project-material';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-project-material',
  templateUrl: './project-material.component.html',
  styleUrls: ['./project-material.component.css']
})
export class ProjectMaterialComponent implements OnInit {

  // parent fields
  @Input() projectMaterial: any;

  //fields

  //faIcons
  faFloppyDisk = faFloppyDisk;
  constructor() { }

  ngOnInit(): void {
  }

}
