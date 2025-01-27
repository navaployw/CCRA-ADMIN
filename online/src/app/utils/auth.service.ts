import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ApiService } from '../apiclient/api.service';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, map, delay } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { SessionStorageService } from 'ngx-webstorage';
import { LoginComponent } from '../login/login.component';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(public _api: ApiService, private router: Router,public login:LoginComponent,private sessionSt: SessionStorageService) {}
  
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log("this._api.isShowMenu", this.sessionSt.retrieve("loginFlag"))
    if (this.sessionSt.retrieve("loginFlag")) {
      return true;
    } else {
      this.router.navigate(['/login'], {
        queryParams: {
          return: state.url
        }
      });
      return false;
    }
  }
}