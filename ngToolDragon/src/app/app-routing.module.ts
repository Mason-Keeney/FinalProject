import { ProjectViewComponent } from './components/project-view/project-view.component';
import { AddressComponent } from './components/address/address.component';
import { RegisterComponent } from './components/register/register.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ProjectComponent } from './components/project/project.component';
import { ToolComponent } from './components/tool/tool.component';
import { ProjectToolComponent } from './components/project-tool/project-tool.component';
import { MaterialComponent } from './components/material/material.component';
import { UserHomeComponent } from './components/user-home/user-home.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'userhome', component: UserHomeComponent},
  { path: 'address', component: AddressComponent},
  { path: 'tool', component: ToolComponent},
  { path: 'project', component: ProjectComponent},
  { path: 'projectView', component: ProjectViewComponent},
  { path: 'projectTool', component: ProjectToolComponent},
  { path: 'material', component: MaterialComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
