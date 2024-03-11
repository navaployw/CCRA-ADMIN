import { Component, OnInit, ViewChild, ElementRef, ChangeDetectorRef} from '@angular/core';
import { DatePipe } from '@angular/common'
import { ApiService } from '../apiclient/api.service';
import { CardListComponent } from '../component/card-list/card-list.component';
import { DialogOpener, DialogType, ButtonMode } from './../component/dialog/dialog-opener';
import {MatDialog} from '@angular/material/dialog';
import {Md5} from 'ts-md5/dist/md5';
import {MatSelect} from '@angular/material/select';


@Component({
  selector: 'app-user-setting',
  templateUrl: './user-setting.component.html',
  styleUrls: ['./user-setting.component.css']
})
export class UserSettingComponent implements OnInit {

  protected dialogOpener: DialogOpener;
  user:ApiUser = new ApiUser();
  mode:string = "new";
  newUserList:any[];
  lockEditing:boolean=true;
  selectedUserLabel:string = "";
  today:Date = new Date();

  constructor(
    public _api: ApiService, 
    protected dialog: MatDialog,
    private changeDetector: ChangeDetectorRef,
    private datepipe: DatePipe
  ) {
    this.dialogOpener = new DialogOpener(dialog);
  }

  ngOnInit(): void {
    this.newUserInfo();
    this.getDataBlockMasterList()
  }

  newUserInfo(){
    this.oldUserIDSearch = "OMFGHELLODUMMYLAHLAHLAH";
    this.userIDSearch = this.selectedUserLabel = '';
    this.mode = "new";
    this.clearApiUserData();
    this.lockEditing = true;
  }
  @ViewChild('focusSearchBar') focusSearchBar: ElementRef;
  focusSearch(){
    this.changeDetector.detectChanges();//https://stackoverflow.com/a/46043837
    this.focusSearchBar.nativeElement.focus()
  }

  getDataBlockMasterList(){
    this._api.getListDataBlock((res:any) =>{
      this.user.setDataBlocksMaster(res);
    });
  }

  @ViewChild(CardListComponent) card:CardListComponent;
  clickAPIuser(aid:number, uid:number, userLabel:string){
    this.mode = "update";
    this._api.getUserDetailByAID(aid, (res:any) =>{
      this.user.setInfo(res);
    });
    this._api.getListDataBlockByUID(uid, (res:any) =>{
      this.user.setDataBlocksData(res);
    });
    this.selectedUserLabel = userLabel;
    this.lockEditing = false;
  }

  saveUserInfo(){
    if(!this.user.secretKey){
      this.dialogOpener.openFailDialog(undefined, "You must give an 'API Secret ID' before saving");
      return;
    }
    if(this.user.secretKey.length != 32){
      this.dialogOpener.openFailDialog(undefined, "'API Secret ID' requires " + (32 - this.user.secretKey.length) + " more character(s)");
      return;
    }
    if(!this.user.secretStart){
      this.dialogOpener.openFailDialog(undefined, "You must give the starting of 'Valid Date' before saving");
      return;
    }
    if(!this.user.secretEnd){
      this.dialogOpener.openFailDialog(undefined, "You must give the ending of 'Valid Date' before saving");
      return;
    }
    if(!this.user.thresholdWeek){
      this.dialogOpener.openFailDialog(undefined, "You must give a '#of call threshold Per Week' before saving");
      return;
    }
    let startDate =this.datepipe.transform(this.user.secretStart.toLocaleString().split(',')[0], 'yyyy-MM-dd');
    let endDate = this.datepipe.transform(this.user.secretEnd.toLocaleString().split(',')[0], 'yyyy-MM-dd');
    this.user.secretStart = startDate;
    this.user.secretEnd = endDate;
    this.user.by =Number(localStorage.getItem("userId"));
    if(this.mode != "new"){
      this._api.isTokenActiveByAID(
        this.user.aid, 
        (res:any) =>{
          console.log("isTokenActiveByAID: " +this.user.aid +" " + res);
          let msg = res ? "This user is currently using" : "";//all of these if-else and function separation just for this one line of code to put a sub message on the dialog...
          this.showDialogBeforeSavingUser(msg);
        },
        (errorRes:any) =>{
          this.dialogOpener.openFailDialog(undefined, errorRes.error);
        },
      );
    }
    else
      this.showDialogBeforeSavingUser("");    
  }
  showDialogBeforeSavingUser(msg:any){
    const saveDialogRef = this.dialogOpener.openSaveDialog(
      "Do you want to save this user?",
      msg,
      {confirm_wording:"Save"}
    );
    saveDialogRef.afterClosed().subscribe(result => {
      console.log("Result::",result);
      if (result.isSaveClicked) 
        this.callSaveUserApi();
    });
  }
  callSaveUserApi():any{
    this._api.saveUserInfo(
      this.createJsonUserData(), 
      (res:any) =>{
        this.user.aid = res;
        this.mode = "update";
        for(let i = 0;i<this.user.dataBlocks.length;i++)
          this.user.dataBlocks[i].old_checked = this.user.dataBlocks[i].checked;

        console.log("this.user::",this.user);
        
        this._api.getViewApiUser(
          {'ai_code':this.user.aiCode,'username':this.user.userID},
          (res:any) =>{
            if(res.length > 0){
              console.log("res:getViewApiUser:",res[0]);
              let requestuser  = {'ai_code':res[0].aiCode,'username':res[0].userID,'password':res[0].password};
              this._api.addUserToAuthenServer(requestuser,(res:any)=>{
                console.log("res:addUserToAuthenServer:",res);
                const successDialogRef = this.dialogOpener.openSuccessDialog("Success");
                successDialogRef.afterClosed().subscribe(res=>{
                  window.location.reload();
                });
                // if(res.entries!=undefined){
                //   console.log("res.entries::",res.entries);
                //   if(res.entries.code=='200'){
                //     const successDialogRef = this.dialogOpener.openSuccessDialog("Success");
                //     successDialogRef.afterClosed().subscribe(res=>{
                //       window.location.reload();
                //     });
                //   }else{
                //     this.dialogOpener.openFailDialog("Fail", "Fail");
                //   }
                // }
              });
            }else{
              const successDialogRef = this.dialogOpener.openSuccessDialog("Success");
                successDialogRef.afterClosed().subscribe(res=>{
                  window.location.reload();
                });
              }
          },
          (errorRes:any) =>{
            this.dialogOpener.openFailDialog(undefined, errorRes.error);
          },
        );
      },
      (errorRes:any) =>{
        this.dialogOpener.openFailDialog(undefined, errorRes.error);
      },
    );
  }
  
  createJsonUserData() :any{
    let userData:any = {
      delDataBlockIDs : [],
      newDataBlockIDs : []
    };
    for (var member in this.user){
      if(member != "dataBlocks")
        userData[member] = this.user[member as keyof ApiUser];//https://stackoverflow.com/questions/57086672/element-implicitly-has-an-any-type-because-expression-of-type-string-cant-b
      else
        for(let i = 0;i<this.user.dataBlocks.length;i++){
          let item = this.user.dataBlocks[i];
          if(item.old_checked != item.checked){
            if(item.checked)
              userData.newDataBlockIDs.push(item.blockID);
            else
              userData.delDataBlockIDs.push(item.blockID);
          }
        };
    }
    return userData;
  }

  deleteUser(){
    if(this.mode == "update"){
      const saveDialogRef = this.dialogOpener.openSaveDialog("Do you want to delete '" + this.user.userID + "'", "", {confirm_wording:"Delete"});
      saveDialogRef.afterClosed().subscribe(retPopUp => {
        if(retPopUp.isSaveClicked){
          this._api.deleteUserByAIDandUID(
            Number(localStorage.getItem("uid")), this.user.aid, this.user.uid, 
            (res:any) =>{
              const successDialogRef = this.dialogOpener.openSuccessDialog(res);
              successDialogRef.afterClosed().subscribe(res=>{
                window.location.reload();
              });
            },
            (error:any) =>{
              this.dialogOpener.openFailDialog(error);
            }
          );
        }
      });
      this.lockEditing = true;
      this.mode = "new";
    }
    else
      this.dialogOpener.openWarningDialog("You must select one of existing API users to be deleted");
  }

  keyGendor = new SecretKeyGenerator();
  genSecret(){
    try{
      this.user.secretKey = this.keyGendor.genSecret(this.user);
    }
    catch(e){
      this.dialogOpener.openFailDialog(undefined, e+"");
    }
  }

  @ViewChild('selectNewUserList') newUserListEref: MatSelect;//https://stackoverflow.com/questions/47878480/angular-2-material-mat-select-programmatically-open-close/50068726#50068726
  userIDSearch:string;
  oldUserIDSearch:string;
  userSelectList:any[];
  findUserId(){
    this.userIDSearch = this.userIDSearch.trim();

    if(this.oldUserIDSearch != this.userIDSearch){
      this.oldUserIDSearch = this.userIDSearch;

      this._api.searchWithUserId(this.userIDSearch, (res:any) =>{
        console.log(res);
        this.userSelectList = res;

        this.changeDetector.detectChanges();//https://stackoverflow.com/a/46043837
        this.newUserListEref.toggle();
      });
    }
    else if(this.userSelectList)
      this.newUserListEref.toggle();
  }
  selectUserFromSearchList(user:any){
    this.userIDSearch = user.AICODE + ": " + user.USERID
    console.log("this.userIDSearch ::",this.userIDSearch );
    if(this.oldUserIDSearch == this.userIDSearch)
      return;

    this.selectedUserLabel = this.oldUserIDSearch = this.userIDSearch;

    this.clearApiUserData();

    this._api.checkUserDuplicateAI(user.UID, user.GROUPID,user.GROUPAIID,
      (res:any) =>{
        if(res.chk){
          this.dialogOpener.openFailDialog(undefined, res.chk_remark);
          this.lockEditing = true;
          return;
        }

        this.mode = "new";
        this.user.uid = user.UID;
        this.user.groupid = user.GROUPID;
        this.user.groupaiid = user.GROUPAIID;
        this.user.userID = user.USERID;
        this.user.aiCode = user.AICODE;
        this.user.groupInfo = user.GROUP_NAME_EN;
        this.lockEditing = false;
        this.genSecret();
      },
      (error:any) =>{
        this.dialogOpener.openFailDialog(undefined, error);
        this.lockEditing = true;
      }
    );
  }
  clearApiUserData(){
    for (var member in this.user){
      if(member != "dataBlocks")
        delete this.user[member as keyof ApiUser];//https://stackoverflow.com/questions/57086672/element-implicitly-has-an-any-type-because-expression-of-type-string-cant-b
      else
        for(let i=0;i<this.user.dataBlocks.length;i++)
          this.user.dataBlocks[i].old_checked = this.user.dataBlocks[i].checked = false;
    }
  }
  adjustThreshold(){
    if(this.user.thresholdWeek < 0)
      this.user.thresholdWeek = 0;
    else if(this.user.thresholdWeek > 9999)
      this.user.thresholdWeek = 9999;
  }
}

class ApiUser{
  userID:string;
  aid:number;
  aiCode:string;
  uid:number;
  groupid:number;
  groupaiid:number;
  groupInfo:string;
  thresholdWeek:number;
  secretKey:any;
  secretStart:any;
  secretEnd:any;
  disabled:boolean;

  dataBlocks:DataBlock[];

  by:number;

  constructor(){
    this.by = Number(localStorage.getItem("uid"));
  }

  setDataBlocksMaster(res:DataBlock[]){
    this.dataBlocks = res;
  }

  setInfo(res:any){
    this.userID = res.userID;
    this.aid = res.aID;
    this.uid = res.uID;
    this.aiCode = res.aiCode;
    this.groupid = res.gid;
    this.groupaiid = res.gaiid;
    this.groupInfo = res.groupInfo;
    this.thresholdWeek = res.thresholdWeek;
    this.secretKey = res.secretKey;
    this.secretStart = res.secretStart;
    this.secretEnd = res.secretEnd;
    this.disabled = res.disabled;
  }

  setDataBlocksData(res:any){
    let userDataBlocks = new Set<number>(res);
    for(let i = 0;i<this.dataBlocks.length;i++){
        this.dataBlocks[i].old_checked = this.dataBlocks[i].checked = userDataBlocks.has(this.dataBlocks[i].blockID);
    }
  }
}
class DataBlock{
  checked:boolean = false;
  old_checked:boolean = false;
  blockID:number;
  blockName:string;
}

class SecretKeyGenerator{
  genSecret(user: ApiUser) : string{
    if(user)
      if(user.uid && user.groupid && user.groupaiid)
        return this.genKey(user);
      else
        console.log(user.uid+ " " + user.groupid + " " +user.groupaiid);

    throw "Not Enough information to generate a key";
  }
  genKey(user: ApiUser) : string{
    const md5Str = new Md5().appendStr(this.genSecretRawString(user)).end();

    let ret = [];    
    const specialChar = "!@#$%^&*()-=+.;:";
    for(let i=0; i < md5Str.length; i++){
      let rand = this.randomInt(10);
      let char = md5Str[i] as string;

      if(rand < 2)
        ret.push(specialChar[this.randomInt(specialChar.length)]);          
      else if(rand < 3 && /^[a-z]*$/.test(char))
        ret.push(char.toUpperCase());
      else
        ret.push(char);
    }

    return ret.join("");
  }
  genSecretRawString(user: ApiUser){
    return this.shuffle([
      new Date().getTime(),
      user.uid,
      user.groupid,
      user.groupaiid,
      user.userID.split('').sort(function(){return 0.5-Math.random()}).join('')
    ]).join("");
  }
  shuffle(array:any[]): any[]{//https://stackoverflow.com/questions/48083353/i-want-to-know-how-to-shuffle-an-array-in-typescript
    let currentIndex = array.length;

    while (currentIndex != 0) {
      let randomIndex = this.randomInt(currentIndex);
      currentIndex--;
  
      [array[currentIndex], array[randomIndex]] = [
        array[randomIndex], array[currentIndex]];
    }
  
    return array;
  };
  randomInt(max:number) {
    return Math.floor(Math.random() * max)
  }
}