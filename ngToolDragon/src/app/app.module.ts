import { ProjectService } from './services/project.service';
import { ProjectComponent } from './components/project/project.component';
import { ToolComponent } from './components/tool/tool.component';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { HomeComponent } from './components/home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavigationComponent } from './components/navigation/navigation.component';
import { RegisterComponent } from './components/register/register.component';
import { UserHomeComponent } from './components/user-home/user-home.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { EdituserComponent } from './components/edituser/edituser.component';
import { AddressComponent } from './components/address/address.component';
import { DatePipe } from '@angular/common';
import { ProjectSearchPipe } from './pipes/project-search.pipe';
import { ProjectViewComponent } from './components/project-view/project-view.component';
import { ToolSearchPipe } from './pipes/tool-search.pipe';
import { ActivePipe } from './pipes/active.pipe';
import { UserSearchPipe } from './pipes/user-search.pipe';
import { ProjectToolComponent } from './components/project-tool/project-tool.component';
import { ParticipantComponent } from './components/participant/participant.component';
import { OwnerPipe } from './pipes/owner.pipe';
import { InspectProjectComponent } from './components/inspect-project/inspect-project.component';
import { MaterialComponent } from './components/material/material.component';
import { ProjectMaterialComponent } from './components/project-material/project-material.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    HomeComponent,
    NavigationComponent,
    RegisterComponent,
    UserHomeComponent,
    EdituserComponent,
    AddressComponent,
    ToolComponent,
    ProjectComponent,
    ProjectSearchPipe,
    ToolSearchPipe,
    ActivePipe,
    ProjectViewComponent,
    ToolSearchPipe,
    UserSearchPipe,
    ProjectToolComponent,
    ParticipantComponent,
    OwnerPipe,
    InspectProjectComponent,
    MaterialComponent,
    ProjectMaterialComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    FontAwesomeModule
  ],
  providers: [
    DatePipe,
    ProjectSearchPipe,
    ActivePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
