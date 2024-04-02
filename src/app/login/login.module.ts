import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginRoutingModule } from './login-routing.module';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    LoginRoutingModule,
  	FormsModule,
  	ReactiveFormsModule,
  ],
  exports:[
  	
    FormsModule
  ]
})
export class LoginModule { }
