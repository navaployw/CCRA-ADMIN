import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ApiService } from '../apiclient/api.service';
import { SessionStorageService } from 'ngx-webstorage';
import { DialogOpener, DialogType, ButtonMode } from './../component/dialog/dialog-opener';
import {MatDialog} from '@angular/material/dialog';
@Component({
  selector: 'app-data-block-setting',
  templateUrl: './data-block-setting.component.html',
  styleUrls: ['../user-setting/user-setting.component.css']
})
export class DataBlockSettingComponent implements OnInit {
  public data:any
  public modelMain:any;
  public modelSearch:any;
  protected dialogOpener: DialogOpener;
  constructor(public _api: ApiService, private sessionSt: SessionStorageService,private router: Router,protected dialog: MatDialog) 
  { 
    this.dialogOpener = new DialogOpener(dialog);
  }

  ngOnInit(): void {
    this.data = [];
    this.modelMain={};
    this.modelMain.status='';
    this.modelMain.userLogin=localStorage.getItem("userId");
    this.modelSearch={searchTxt:''};
    this.getDataBlockList();
  }

  getDataBlockList(){

    this._api.getDataBlockList(this.modelSearch, (res: any) => {
      console.log("getDataBlockList>>>",res)
      this.data = res;
    });
  }
  selectDatablock(data:any){
    console.log("selectDatablock>>>",data);
    this.modelMain = Object.assign({}, data);;
    this.modelMain.status='edit'

  }
  searchTxt(){

    console.log("searchTxt>>>",this.modelSearch);
    this._api.getDataBlockList(this.modelSearch, (res: any) => {
      console.log("getDataBlockList>>>",res)
      this.data = res;
    });
  }
  addDataBlock() {
    var dataBlockSize = 0;
    this._api.getDataBlockList({searchTxt:''}, (res: any) => {
      dataBlockSize = res.length;
      console.log("dataBlockSize>>>",dataBlockSize)
      if (dataBlockSize >= 99) {
        Swal.fire('', 'Maximum number of Alternative Data to be added is 99', 'warning')
      }
      else {
        this.modelMain = {};
        this.modelMain.status = 'new'
        this.modelMain.userLogin = localStorage.getItem("userId");
      }
    });
   

  }

  saveDataBlock(){
    this.modelMain.userLogin=localStorage.getItem("userId");
    if(this.modelMain.blockName==undefined || this.modelMain.blockName==''){
      Swal.fire('', 'Invalid input Alternative Data', 'warning')

    }
    if(this.modelMain.blockUrl==undefined || this.modelMain.blockUrl==''){
      Swal.fire('', 'Invalid input Alternative Data URL', 'warning')

    }
    else{

      const saveDialogRef = this.dialogOpener.openSaveDialog(
        "Do you want to save?",
        '',
        {confirm_wording:"Save"}
      );
      saveDialogRef.afterClosed().subscribe(result => {
        console.log("Result::",result);
        if (result.isSaveClicked) 
        this._api.insertUpdateDataBlockList(this.modelMain, (res: any) => {
          console.log("insertUpdateDataBlockList>>>",res)
          if(res.duplicate){
            Swal.fire('', res.chkRemark, 'warning')
          }
          else{
    
            Swal.fire({title:res.chkRemark,confirmButtonText:'OK'}).then((result) => {
              window.location.reload();
            }); 
    
        
          }
       
        });
      });


    
  }
  }
  removeDataBlock(){

  
    this.modelMain.userLogin=localStorage.getItem("userId");

    
    this._api.beforeRemoveDataBlockList(this.modelMain, (res: any) => {

      console.log("res[0].countUsing",res[0].countUsing);
      if(res[0].countUsing==0){
          const saveDialogRef = this.dialogOpener.openSaveDialog("Do you want to Delete Alternative Data?", "", {confirm_wording:"Delete"});
            saveDialogRef.afterClosed().subscribe(result => {
              console.log("Result::",result);
               if (result.isSaveClicked) {
                  this._api.removeDataBlockList(this.modelMain, (res: any) => {
                    const successDialogRef = this.dialogOpener.openSuccessDialog();
                    successDialogRef.afterClosed().subscribe(res=>{
                      window.location.reload();
                    });
                  });
                } else if (!result.isSaveClicked) {
                }
            });
      }
      else{
        const saveDialogRef = this.dialogOpener.openSaveDialog("Do you want to Delete Alternative Data?",res[0].countUsing + ` user(s) using this Alternative Data`, {confirm_wording:"Delete"});
            saveDialogRef.afterClosed().subscribe(result => {
              console.log("Result::",result);
               if (result.isSaveClicked) {
                  this._api.removeDataBlockList(this.modelMain, (res: any) => {
                    const successDialogRef = this.dialogOpener.openSuccessDialog();
                    successDialogRef.afterClosed().subscribe(res=>{
                      window.location.reload();
                    });
                  });
                } else if (!result.isSaveClicked) {
                }
            });
      }
    });


    


  }
  cancelDataBlock(){
    window.location.reload();

  }
}
