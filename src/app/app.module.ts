import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import { TopBarComponent } from './component/top-bar/top-bar.component';
import { Routes, RouterModule } from '@angular/router';
import { UserSettingComponent } from './user-setting/user-setting.component';
import { DataBlockSettingComponent } from './data-block-setting/data-block-setting.component';
import { UsageReportComponent } from './usage-report/usage-report.component';
import { CardListComponent } from './component/card-list/card-list.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import {MatTabsModule} from '@angular/material/tabs';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import {NgxWebstorageModule} from 'ngx-webstorage';
import { DialogComponent } from './component/dialog/dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { DemoMaterialModule } from './utils/material-module';
import {AuthGuard} from './utils/auth.service';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { DatePipe } from '@angular/common'
import { AuthInterceptor } from './interceptor/http.interceptor';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/login' },
  { path: 'home', component: HomeComponent ,canActivate : [AuthGuard]},
  { path: 'User-Setting', component: UserSettingComponent,canActivate : [AuthGuard] },
  { path: 'Data-Block-Setting', component: DataBlockSettingComponent,canActivate : [AuthGuard] },
  { path: 'Usage-Report', component: UsageReportComponent ,canActivate : [AuthGuard]},
  { path: 'login', component: LoginComponent },
]; 

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    TopBarComponent,
    UserSettingComponent,
    DataBlockSettingComponent,
    UsageReportComponent,
    CardListComponent,
    DialogComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatGridListModule,
    RouterModule.forRoot(routes),
    MatDividerModule,
    MatListModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatDialogModule,
    MatInputModule,
    MatTabsModule,
    FormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    MatIconModule,
    MatSelectModule,
    DemoMaterialModule,
    NzIconModule

  ],
  providers: [DatePipe,   
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
    },
    // {
    //   provide: HttpXsrfTokenExtractor,
    //   useClass: AuthInterceptor
    // }    
  ],
  
  bootstrap: [AppComponent],
  exports: [RouterModule],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule { }
