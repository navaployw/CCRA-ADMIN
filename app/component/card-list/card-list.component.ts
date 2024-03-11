import { Component, Input, Optional, OnInit, ViewChild, Inject } from '@angular/core';
import Swal from 'sweetalert2';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ApiService } from '../../apiclient/api.service';
import { SessionStorageService } from 'ngx-webstorage';
import { UserSettingComponent } from '../../user-setting/user-setting.component';


@Component({
  selector: 'card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.css']
})
export class CardListComponent implements OnInit {
   public data:any[] = [];

  constructor(@Optional() protected parent: UserSettingComponent) { }



  ngOnInit(): void {
    this.parent._api.getAPIUserList((res: any) => {
      console.log("res>>>",res)
      this.data = res;
    });
  }

  public click_user(dataItem:any){
    this.parent.clickAPIuser(
      dataItem.aID, dataItem.uID, 
      (dataItem.aiCode ? dataItem.aiCode : "")  + ": " + dataItem.userID
    );
  }

  public delete_user(){
    this.parent.deleteUser();
  }

  public newUserInfo(){
    this.parent.newUserInfo();
    this.parent.focusSearch();
  }
  

}
