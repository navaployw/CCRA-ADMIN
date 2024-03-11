import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import Swal from 'sweetalert2';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ApiService } from '../../apiclient/api.service';
import { SessionStorageService } from 'ngx-webstorage';

@Component({
  selector: 'top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})

export class TopBarComponent implements OnInit {
    public data = [
    { title : "1 Blank #1"},
    { title : "2 Blank #2"},
    { title : "3 Blank #3"},
    { title : "4 Blank #4"},
    { title : "5 Blank #5"},
  ]
  userLogin:any;
	isLoadMenu = false;
  constructor(public _api: ApiService, private sessionSt: SessionStorageService,private router: Router) { }

  ngOnInit(): void {

   this.userLogin = localStorage.getItem("userLogin")
  }

  public logout() {
    this._api.doLogout();
    this.sessionSt.store("loginFlag",false);
    localStorage.removeItem('Login');
    console.log('userLogin>>>',localStorage.getItem("userId"));
    console.log('groupId>>>',localStorage.getItem("groupId"));

    var json = {userId:localStorage.getItem("userId"),groupId:localStorage.getItem("groupId"),username:localStorage.getItem("userLogin")};
    console.log('json>>>',json);
    this._api.logout(json, (res: any) => {
      console.log(res);
      if(res.code=='200'){
        console.log("Navigate to home:111");
         this.router.navigate(['/login']);
      }else{
        console.log("Error");
      }

    });
    

  }

}
