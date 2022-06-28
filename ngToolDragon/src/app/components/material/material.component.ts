import { ProjectMaterialComponent } from './../project-material/project-material.component';
import { AuthService } from 'src/app/services/auth.service';
import { MaterialsService } from './../../services/materials.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/app/models/user';
import { Material } from 'src/app/models/material';
import { faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { ProjectMaterial } from 'src/app/models/project-material';

@Component({
  selector: 'app-material',
  templateUrl: './material.component.html',
  styleUrls: ['./material.component.css']
})
export class MaterialComponent implements OnInit {

  user: User = new User();
  materialList: Material[] = [];
  selected: ProjectMaterial = new ProjectMaterial();
  addProjectMaterial: boolean = false;

  // faIcons:
  faPlus = faPlusCircle;

  // ChildViews
  @ViewChild(ProjectMaterialComponent, { static: false })
  projectMaterialComponent!: ProjectMaterialComponent;

  constructor(
    private materialService: MaterialsService,
    private authService: AuthService
    ) {}

  ngOnInit(): void {
    this.authenticateUser();
    this.indexMaterials();
  }

  authenticateUser(){
    this.authService.authenticateUser().subscribe({
      next: (result) =>{
        this.user = result;
      },
      error: (problem) => {
        console.log(problem);
      }
    })
  }

  indexMaterials(){
    this.materialService.index().subscribe({
      next: (result) => {
        this.materialList = result;
      },
      error: (problem) => {
        console.log("MaterialHttpComponent Error: unable to populate MaterialList");
        console.log(problem);
      }
    })
  }

  toggleSelected(material: Material){

      if(!this.addProjectMaterial){
        this.selected.material = material;
        this.addProjectMaterial = true;
      } else {
        this.addProjectMaterial = false;
      }
    }

}
