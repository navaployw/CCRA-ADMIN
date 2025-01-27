import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsageReportRoutingModule } from './usage-report-routing.module';
import { DemoMaterialModule } from '../utils/material-module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {  UsageReportComponent } from './usage-report.component';

@NgModule({
  declarations: [UsageReportComponent],
  imports: [
    CommonModule,
    UsageReportRoutingModule,
    DemoMaterialModule,
  	FormsModule,
  	ReactiveFormsModule,
  ],
  exports:[
  	UsageReportComponent,
    FormsModule
  ]
})
export class UsageReportModule { }
