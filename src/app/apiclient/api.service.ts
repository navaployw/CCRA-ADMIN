import { Injectable } from '@angular/core';
import { asyncScheduler, Observable, throwError } from 'rxjs';
import { catchError, map,timeout } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { SessionStorageService } from 'ngx-webstorage';
import { environment } from './../../environments/environment';
import data from './../../../config.json';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  environmentName = environment.hostnameApi;
  public hostnameApi: string = data.hostnameApi;
  public hostnameAuthen: string = data.hostnameAuthen;
  endpoint: string = this.hostnameApi+'/admin';
  endpointAuthen: string = this.hostnameAuthen;
  endpointReport: string = this.hostnameApi+'/report';
  userEndPoint: string = this.hostnameApi+'/user';

  currentUser = {};
  public isLoggedInFlag:Boolean = false;
  public isAdminSystem = false;

  public loaddingclass = "";
  public onLoading = "spinner-on";
  public stopLoading = "";

  public options = {
          headers: {
          }
  }

  

  constructor(
     private http: HttpClient,
     private sessionSt: SessionStorageService
  ) 
  {
    console.log("Hostname api", data.hostnameApi);
    console.log("auth api", data.hostnameAuthen);
   }

   
   public isShowMenu: boolean= true;
   reInitMenu() {
   this.isShowMenu = true;
   
   }
   doLogout(){
   this.isShowMenu = false;
   
   }
   setLoginFlag(flag:Boolean){
     this.isLoggedInFlag = flag;
   }
   getLoginFlag(){
    return this.isLoggedInFlag ;
  }
  loading(){
    console.log(">>>loading<<<");
    this.loaddingclass = this.onLoading;
  }

  loaded(){
    console.log(">>>loaded<<<");
    this.loaddingclass = "";
  }

 

  checkLogin(mainActivity:any,callBack:any) {
    console.log(">>>checkLogin<<<");
    return this.http.post<any>(`${this.endpoint}/checkLogin`,mainActivity)
      .subscribe((res: any) => {
        console.log("return", res);
        callBack(res);
      })

  }
  
  logout(mainActivity:any,callBack:any) {
    console.log(">>>Logout<<<");
    return this.http.post<any>(`${this.endpoint}/logout`,mainActivity)
      .subscribe((res: any) => {
        console.log("return", res);
        callBack(res);
      })

  }

  getDataBlockList(mainActivity:any,callBlack:any) {
    console.log(">>>getDataBlockList<<<");
    return this.http.post<any>(`${this.endpoint}/getDataBlockList`,mainActivity)
      .subscribe((res: any) => {
        console.log("return", res);
        callBlack(res);
      })

  }
  insertUpdateDataBlockList(mainActivity:any,callBlack:any) {
    console.log(">>>insertUpdateDataBlockList<<<");
    return this.http.post<any>(`${this.endpoint}/insertUpdateDataBlockList`,mainActivity)
      .subscribe((res: any) => {
        console.log("return", res);
        callBlack(res);
      })

  }

  removeDataBlockList(mainActivity:any,callBlack:any) {
    console.log(">>>removeDataBlockList<<<");
    return this.http.post<any>(`${this.endpoint}/removeDataBlockList`,mainActivity)
      .subscribe((res: any) => {
        console.log("return", res);
        callBlack(res);
      })

  }
  beforeRemoveDataBlockList(mainActivity:any,callBlack:any) {
    console.log(">>>beforeRemoveDataBlockList<<<");
    return this.http.post<any>(`${this.endpoint}/beforeRemoveDataBlockList`,mainActivity)
      .subscribe((res: any) => {
        console.log("return", res);
        callBlack(res);
      })

  }
  
  
  getReportUsage(params:any,callBack:any){
    console.log(">>>getUsageReportData<<<");
    return this.http.post<any>(`${this.endpointReport}/get_report_usage_all`,params)
    .subscribe((res:any) =>{
      console.log("return;report:all:",res);
      callBack(res);
    })

  }
  getReportDischargeData({},callBack:any){
    console.log(">>>getReportDischargeData<<<");
    return this.http.post<any>(`${this.endpointReport}/get_report_discharge`,{})
    .subscribe((res:any) =>{
      console.log("return;report:",res);
      callBack(res);
    })

  }

  getUsageSummaryReportData(params:any,callBack:any){
    console.log(">>>getUsageReportData<<<");
    return this.http.post<any>(`${this.endpointReport}/get_report_usage_sum`,params)
    .subscribe((res:any) =>{
      console.log("return;report:",res);
      callBack(res);
    })

  }

    searchWithUserId(userId:string, callBlack:any) {
    console.log(">>>searchWithUserId<<<");
    return this.http.get<any>(`${this.userEndPoint}/searchWithUserId/`+(userId ? userId:""))
      .subscribe((res: any) => {
        console.log("return", res);
        callBlack(res);
      })
  }
  checkUserDuplicateAI(uid:number,gid:number,gaiid:number, callBlack:any, errorCallBlack:any) {
    console.log(">>>checkUserDuplicateAI<<<");
    return this.http.get<any>(`${this.userEndPoint}/checkUserDuplicateAI/`+uid+'/'+gid+'/'+gaiid)
      .subscribe(
        (res: any) => {
          console.log("return", res);
          callBlack(res);
        },
        (res: any) => {
          console.log("error", res);
          errorCallBlack(res);
        },
      )
  }

  getAPIUserList(callBlack:any) {
    console.log(">>>getAPIUserList<<<");
    return this.http.get<any>(`${this.userEndPoint}/getAPIUserList`)
      .subscribe((res: any) => {
        console.log("return", res);
        callBlack(res);
      })

  }
  getUserDetailByAID(uid:number ,callBlack:any) {
    console.log(">>>getUserDetailByAID<<<");
    return this.http.get<any>(`${this.userEndPoint}/getUserDetailByAID/` + uid)
      .subscribe((res: any) => {
        console.log("return", res);
        callBlack(res);
      })

  }
  getListDataBlock(callBlack:any) {
    console.log(">>>getListDataBlock<<<");
    return this.http.get<any>(`${this.userEndPoint}/getListDataBlock`)
      .subscribe((res: any) => {
        console.log("return", res);
        callBlack(res);
      })

  }
  isTokenActiveByAID(aid:number ,callBlack:any, callBackError:any) {
    console.log(">>>isTokenActiveByAID<<<");
    return this.http.get<any>(`${this.userEndPoint}/isTokenActiveByAID/` + aid)
      .subscribe(
        (res: any) => {
          console.log("return", res);
          callBlack(res);
        },
        (res: any) => {
          console.log("error", res);
          callBackError(res);
        }
      )
  }
  getListDataBlockByUID(uid:number ,callBlack:any) {
    console.log(">>>getListDataBlockByUID<<<");
    return this.http.get<any>(`${this.userEndPoint}/getListDataBlockByUID/` + uid)
      .subscribe((res: any) => {
        console.log("return", res);
        callBlack(res);
      })
  }

  saveUserInfo(user:any ,callBlack:any, callBackError:any) {
    console.log(">>>saveUserInfo<<<");
    console.log(user);
    return this.http.post<any>(`${this.userEndPoint}/saveUserInfo`, user, {responseType: 'text' as 'json'})
      .subscribe(
        (res: any) => {
          console.log("return", res);
          console.log(user);
          callBlack(res);
        },
        (res: any) => {
          console.log("error", res);
          console.log(user);
          callBackError(res);
        },
      )
  }

  deleteUserByAIDandUID(by:number ,aid:number ,uid:number ,callBlack:any, callBackError:any) {
    console.log(">>>deleteUserByAIDandUID<<<");
    return this.http.delete<any>(`${this.userEndPoint}/deleteUserByAIDandUID/` + by + "/"+ aid + "/"+ uid, {responseType: 'text' as 'json'})
      .subscribe(
        (res:any) => {
          console.log("return", res);
          callBlack(res);
        },
        (error:any) => {
          console.log("error", error);
          callBackError(error);
        }
      )
  }

  getMemberList(callBack:any){
    console.log(">>>getMemberList<<");
    return this.http.get<any>(`${this.endpoint}/getMemberList`)
      .subscribe(
        (res:any) => {
          callBack(res);
        });
  }

  getViewApiUser(requestData:any,callBack:any,callBackError:any){
    console.log(">>>getViewApiUser<<<");
    console.log("RequestData: ",requestData);
    return this.http.post<any>(`${this.endpoint}/getViewApiUser`,requestData)
    .subscribe(
      (res:any) => {
        console.log("return:res:", res);
        callBack(res);
      },
      (res: any) => {
        console.log("error", res);
        callBackError(res);
      }
    );
  }

  addUserToAuthenServer(requestData:any,callBack:any){
    console.log(">>>addUserToAuthenServer<<<");
    const headers = { Authorization: null }; // or const headers = {};
    return this.http.post<any>(`${this.endpointAuthen}/registerUser`, requestData)
    .subscribe(
        (res:any) => {
          console.log("return:addUserToAuthenServer:", res);
          callBack(res);
        });
  }

  getVersionNo(callBack:any){
    return this.http.get<any>(`${this.endpoint}/getVersionNo`)
    .subscribe((res:any) => {
      console.log("return:res:", res[0].ctrlValue);
      callBack(res[0].ctrlValue);
    });
  }


  

}
