import { Component, Inject, ViewEncapsulation } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export enum ButtonMode{
	NO_BTN,
	ONE_BTN,
	YES_NO_BTN,
}

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent {
	
	ButtonMode=ButtonMode;

	constructor(
		public dialogRef: MatDialogRef<DialogComponent>
		,@Inject(MAT_DIALOG_DATA) public pageInfo: any) {
		console.log(this.pageInfo);
		this.dialogRef.disableClose = true;
	}

	onSaveClick(){
		this.dialogRef.close({
			isSaveClicked:true,
		}); //[mat-dialog-close]="data"
	}

	onNoClick(): void {
		this.dialogRef.close(false);
	}
	onClickWithResult(){
		this.dialogRef.close(this.pageInfo.buttonResult);		
	}
	

}
