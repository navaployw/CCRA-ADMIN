import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeRoutingModule } from './home-routing.module';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home.component';

@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
  	FormsModule,
  	ReactiveFormsModule,
  ],
  exports:[
  	HomeComponent,
    FormsModule
  ]
})
export class HomeModule { }
