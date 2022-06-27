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
    ProjectViewComponent,
    ToolSearchPipe
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
    ProjectSearchPipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
