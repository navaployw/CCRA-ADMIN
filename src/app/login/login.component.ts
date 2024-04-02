import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import Swal from 'sweetalert2';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ApiService } from '../apiclient/api.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Injectable } from '@angular/core';
import { UserInfo } from '../model/UserInteface';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
@Injectable({
  providedIn: 'root'
})

export class LoginComponent implements OnInit {
  modelMain: UserInfo;
  isLoadingOne = false;
  isLoadMenu = false;

  constructor(public _api: ApiService, private sessionSt: SessionStorageService,private router: Router) { 


  }

  ngOnInit(): void {
    this._api.doLogout();
    this.modelMain = {username: '',pwd:''};

  }
  hide: boolean = true;

  showPwd() {
    this.hide = !this.hide;
  }
  public login() {

    if (this.modelMain.username == undefined || this.modelMain.username == '') {
      Swal.fire('', 'User ID is Required', 'warning')
    }
    else if (this.modelMain.pwd == undefined || this.modelMain.pwd == '') {
      Swal.fire('', 'Password is Required', 'warning')
    } else {

      this._api.checkLogin(this.modelMain, (res: any) => {
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
