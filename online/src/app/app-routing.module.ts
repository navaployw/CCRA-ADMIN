import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuard} from './utils/auth.service';

// const routes: Routes = [
//   { path: '',canActivate : [AuthGuard], loadChildren: () => import('./home/home.module').then(m => m.HomeModule)},
//   { path: 'User-Setting',canActivate : [AuthGuard], loadChildren: () => import('./user-setting/user-setting.module').then(m => m.UserSettingModule)},
// ];

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
