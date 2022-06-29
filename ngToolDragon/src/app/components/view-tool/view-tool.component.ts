import { ProjectTool } from 'src/app/models/project-tool';
import { ToolPresentPipe } from './../../pipes/tool-present.pipe';
import { ProjectToolService } from './../../services/project-tool.service';
import { faCircleXmark, faCheck, faClock } from '@fortawesome/free-solid-svg-icons';
import { Component, Input, OnInit } from '@angular/core';
import { Tool } from 'src/app/models/tool';
import { Project } from 'src/app/models/project';

@Component({
  selector: 'app-view-tool',
  templateUrl: './view-tool.component.html',
  styleUrls: ['./view-tool.component.css']
})
export class ViewToolComponent implements OnInit {

  //inhereited fields
  @Input() inheritedTool: any;

  //fields
  tool: Tool = new Tool();
  projectToolList: ProjectTool[] = [];
  projectToolsOnTool: ProjectTool[] = [];
  today = new Date();


  //fa-icons
  faCircleXmark = faCircleXmark;
  faCheck = faCheck;
  faClock = faClock;

  constructor(
    private projectToolService: ProjectToolService,
    private toolPresentPipe: ToolPresentPipe
  ) { }

  ngOnInit(): void {
    this.tool = this.inheritedTool
    this.indexProjectTools();
  }

  indexProjectTools(){
    this.projectToolService.index().subscribe({
      next: (result) => {
        this.projectToolList = result;
        this.projectToolsOnTool = this.toolPresentPipe.transform(this.projectToolList, this.tool);
      },
      error: (problem) => {
        console.log("ViewProjectToolHttpComponent Error: unable to populate ProjectTools");
        console.log(problem);
      }
    })
  }

  reload(){
    this.indexProjectTools();
  }

  deleteProjectTool(projectTool: ProjectTool){
    let tempProject = projectTool.project;
    if(tempProject && this.tool){
      this.projectToolService.destroy(tempProject.id, this.tool.id).subscribe({
        next: () => {
          this.reload();
        },
        error: (problem) => {
          console.log("ViewProjectToolHttpComponent Error: unable to remove ProjectTool" );
          console.log(problem);
        }
      })

    }
  }

  approveProjectTool(projectTool: ProjectTool){
    let tempProject = projectTool.project;
    projectTool.dateApproved = this.today;
    projectTool.project = null;
    projectTool.tool = null;
    if(tempProject){
      this.projectToolService.update(tempProject.id, this.tool.id, projectTool).subscribe({
        next: (result) => {
          projectTool = result;
          this.reload();
        },
        error: (problem) => {
          console.log("ViewProjectToolHttpComponent Error: Unable to approve ProjectTool");
          console.log(problem)
        }
      })
    }
  }

}
