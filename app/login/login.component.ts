import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import Swal from 'sweetalert2';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ApiService } from '../apiclient/api.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Injectable } from '@angular/core';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
@Injectable({
  providedIn: 'root'
})

export class LoginComponent implements OnInit {
  modelMain: any;
  isLoadingOne = false;
  isLoadMenu = false;

  constructor(public _api: ApiService, private sessionSt: SessionStorageService,private router: Router) { 


  }

  ngOnInit(): void {
    this._api.doLogout();
    this.modelMain = {};

  }
  hide: boolean = true;

  showPwd() {
    this.hide = !this.hide;
  }
  public login() {
    console.log("this.modelMain>>>", this.modelMain);

    if (this.modelMain.username == undefined || this.modelMain.username == '') {
      Swal.fire('', 'User ID is Required', 'warning')
    }
    else if (this.modelMain.pwd == undefined || this.modelMain.pwd == '') {
      Swal.fire('', 'Password is Required', 'warning')
    } else {

      this._api.checkLogin(this.modelMain, (res: any) => {
        console.log("res>>>",res)
        if(res.result){
          this.sessionSt.store("loginFlag",true);
          this._api.reInitMenu();
          localStorage.setItem('Login', "Login success");
          localStorage.setItem('userLogin', this.modelMain.username);
          localStorage.setItem('uid', res.userId);
          localStorage.setItem('userId', res.userId);
          localStorage.setItem('groupId', res.groupId);
          localStorage.setItem('groupAIID',res.groupAIID);

          this.router.navigate(['/home']);
        
        }else{
          Swal.fire('', res.message, 'warning')
        }

      });

    }

  }
}
