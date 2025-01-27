import { Component } from '@angular/core';
import { MatDialog, MatDialogRef} from '@angular/material/dialog';
import { DialogComponent } from './dialog.component';
export enum DialogType{
	FAIL="fail.png",
	LOADING="loading.gif",
	SAVE="save.png",
	SUCCESS="success.png",
	WARNING="warning.png",
	DELETE="delete.png",
	TRANSFER="transfer.png"
}
export enum ButtonMode{
	NO_BTN,
	ONE_BTN,
	YES_NO_BTN,
}

export class DialogOpener {
	
	pageModeToPicMapper:any = {
		Withhold: DialogType.SAVE,
		Allow: DialogType.SAVE,
		Edit: DialogType.SAVE,
		Delete: DialogType.DELETE,
		Transfer: DialogType.TRANSFER,
	}
	imgPath:string = "assets/image/dialog/"

	constructor(protected dialog: MatDialog) {}
	
	openDialog(//: MatDialogRef<DialogComponent, any>
		dialogType:DialogType,
		message:{title:string,body:string},
		BtnMode:ButtonMode
		) : MatDialogRef<DialogComponent, any>{

		return this.dialog.open(
			DialogComponent, 
			{ 
				width: '450px',
				data:{
					imageSrc: this.imgPath + dialogType,
					message:message,
					BtnMode:BtnMode
				}
			}
		);
	}

	openSaveDialog(title?:string, body?:string, showInfo?:any) : MatDialogRef<DialogComponent, any>{
		return this.dialog.open(
			DialogComponent, 
			{ 
				width: '850px',
				data:{
					imageSrc: this.imgPath + (showInfo && showInfo.pageMode ? this.pageModeToPicMapper[showInfo.pageMode] : DialogType.SAVE),
					message:{
						title: title != undefined ? title : "Do you want to save the changes?", 
						body: body != undefined ? body : "Please confirm"
					},
					BtnMode:ButtonMode.YES_NO_BTN,
					showInfo:showInfo
				}
			}
		);
	}
	openWarningDialog(title?:string, body?:string) : MatDialogRef<DialogComponent, any>{
		return this.dialog.open(
			DialogComponent, 
			{ 
				width: '450px',
				data:{
					imageSrc: this.imgPath + DialogType.WARNING,
					message:{
						title: title != undefined ? title:"Please fill every required Field and input the right format", 
						body: body != undefined ? body : ""
					},
					BtnMode:ButtonMode.ONE_BTN
				}
			}
		);
	}
	openSuccessDialog(title?:string, body?:string) : MatDialogRef<DialogComponent, any>{
		return this.dialog.open(
			DialogComponent,
			{
				width: '450px',
				data:{
					imageSrc: this.imgPath + DialogType.SUCCESS,
					message:{
						title: title != undefined ? title : "Success",
						body: body != undefined ? body : ""
					},
					BtnMode:ButtonMode.ONE_BTN,
					buttonResult:true
				}
			}
		);
	}

	openFailDialog(title?:string, body?:string) : MatDialogRef<DialogComponent, any>{
		return this.dialog.open(
			DialogComponent,
			{
				width: '450px',
				data:{
					imageSrc: this.imgPath + DialogType.FAIL,
					message:{
						title: title != undefined ? title :"Failed",
						body: body != undefined ? body :"Something went wrong"
					},
					BtnMode:ButtonMode.ONE_BTN,
					buttonResult:false
				}
			}
		);
	}
	openLoadingDialog(title?:string, body?:string) : MatDialogRef<DialogComponent, any>{
		return this.dialog.open(
			DialogComponent,
			{
				width: '450px',
				data:{
					imageSrc: this.imgPath + DialogType.LOADING,
					message:{
						title: title != undefined ? title : "Please wait",
						body: body != undefined ? body : ""
					},
					BtnMode:ButtonMode.NO_BTN
				}
			}
		);
	}
}
