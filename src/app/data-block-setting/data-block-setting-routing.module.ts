import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DataBlockSettingComponent} from './data-block-setting.component'
const routes: Routes = [{path:'', component:DataBlockSettingComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DataBlockSettingRoutingModule { }
