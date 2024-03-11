import { Component, OnInit } from '@angular/core';
import { ApiService } from './apiclient/api.service';
import { environment } from './../environments/environment';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  public isLogin:boolean = false;
  public thisYear:any;
  public versionNo:any;
  title = 'ccraapi';
  environmentName = environment.hostnameApi;
  constructor(public _api: ApiService) { }

  ngOnInit(): void {
    console.log("EnvironmentName:::",this.environmentName);
    var isLogin = localStorage.getItem('Login');
    this.isLogin = isLogin !== null?true:false;
    this.thisYear = new Date().getFullYear();
    this._api.getVersionNo((res:any)=>{
      this.versionNo = res;
    });
  }
  
}
