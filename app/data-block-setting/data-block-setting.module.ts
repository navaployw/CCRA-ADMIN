import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DataBlockSettingRoutingModule } from './data-block-setting-routing.module';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {  DataBlockSettingComponent } from './data-block-setting.component';

@NgModule({
  declarations: [DataBlockSettingComponent],
  imports: [
    CommonModule,
    DataBlockSettingRoutingModule,
  	FormsModule,
  	ReactiveFormsModule,
  ],
  exports:[
  	DataBlockSettingComponent,
    FormsModule
  ]
})
export class DataBlockSettingModule { }
