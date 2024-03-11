import { Component, OnInit,AfterViewInit, ViewChild } from '@angular/core';
import { DatePipe } from '@angular/common';
import { HttpClient} from '@angular/common/http';
import Swal from 'sweetalert2';
import { ApiService } from '../apiclient/api.service';
import * as XLSX from 'xlsx';
import * as FileSaver from 'file-saver';
import { SessionStorageService } from 'ngx-webstorage';
import { Angular5Csv } from 'angular5-csv/dist/Angular5-csv';
import * as JSZip from 'jszip';
const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';

@Component({
  selector: 'app-usage-report',
  templateUrl: './usage-report.component.html',
  styleUrls: ['./usage-report.component.css']
})
export class UsageReportComponent implements  OnInit{
  dataSourceExport: any[] = [];
  modelDate:any;
  params:any;
  memberList:any;
  loaded:boolean = false;
  sortingPrimary:any;
  sortingSecond:any;
  memberSorting:any;
  memberListSorting:any;
  sumcsvdata:any;
  transcsvdata:any;
  dischargecsvdata:any;
  constructor(public _api: ApiService,private sessionSt: SessionStorageService) { }

  ngOnInit(): void {
    this.modelDate = {startDate:'',endDate:''}
    this.params = {uId:Number(localStorage.getItem("uid")),startDate:this.modelDate.startDate,endDate:this.modelDate.endDate,whereCase:0,groupAiId:Number(localStorage.getItem("groupAIID")),groupId:Number(localStorage.getItem("groupId")),monthlyPlan:undefined,sortPri:'U',sortSec:'D',sortMem:'C'};
    this._api.getMemberList((res:any)=>{
      if(res!=undefined){
        this.memberList = res;
      }
    });
  }
  selectSortPrimary(value:any){
    if(value!=undefined){
      this.params.sortPri  = value;
    }
  }
  selectSortSecondary(value:any){
    if(value!=undefined){
      this.params.sortSec  = value;
    }
  }
   selectSortMember(value:any){
    if(value!=undefined){
      this.params.sortMem  = value;
    }
  }
  selectMemberList(value:any){
    if(value!=undefined){
      if(value.includes("'")){
        this.params.whereCase = 2;//.split("'")
        this.params.monthlyPlan = value.replaceAll("'","");

      }else if(value==""){
        this.params.whereCase = 0;
        this.params.monthlyPlan = value.replaceAll("'","");;
      }else{
        this.params.whereCase = 3;
        this.params.groupId = value.replaceAll("'","");;
      }

    }
  }
  exportExcel = (): any => {
    this._api.loading();
    let diffYear = this.modelDate.endDate.getFullYear()-this.modelDate.startDate.getFullYear();
    let diffMonth = this.modelDate.endDate.getMonth()-this.modelDate.startDate.getMonth();
    if(diffYear > 1 || (diffYear ==1 && diffMonth>0)){
      this._api.loaded();
      this.loaded = true;
      Swal.fire('', 'Period cannot be longer than a year', 'warning');
    }else{
      let resDischarge = '';
      let resUsage = '';
      let resTrans = '';
      this.params.reportDate = new Date();
      this.params.userId = localStorage.getItem("userLogin")
      this.params.startDate = this.modelDate.startDate;
      this.params.endDate = this.modelDate.endDate
      this._api.getReportUsage(this.params,(res:any)=>{
        if(res.usageSummary.length > 0 || res.dischargeReport.length > 0){
          this.dataSourceExport.push(res);

          resUsage = res.usageSummary;
          resDischarge = res.dischargeReport;
          resTrans = res.transactionReport;
          var objKey1 = ['Report Date','Requested By','Report Period','Member code','Member Name','Product Code','Product','Usage']
          var objKey2 = ['Report Date','Requested By','Report Period','Member Code','Member Name','Report Ordered Date & Time','HKBRC','HKCI','Other Registration / Incorporation Number','Place of Registration / Incorporation','Customer Number','Location / Branch ID','Account Manager Code','Reason Code','User ID','Product Code','Product Name','Report Ref. No.','AI Reference Code 1','AI Reference Code 2','AI Reference Code 3','Discharge Date and Time','Status']
          var objKey3 = ['Report Date','Requested By','Report Period','Member Code','Member Name','Report Ordered Date & Time','HKBRC','HKCI','Other Registration / Incorporation Number','Place of Registration / Incorporation','Customer Number','Location / Branch ID','Account Manager Code','Reason Code','User ID','Product Code','Product Name','Report Ref. No.','AI Reference Code 1','AI Reference Code 2','AI Reference Code 3']
          let fieldHeaderData1 = []
          fieldHeaderData1.push(JSON.parse(JSON.stringify(objKey1)));
          let fieldHeaderData2 = []
          fieldHeaderData2.push(JSON.parse(JSON.stringify(objKey2)));
          let fieldHeaderData3 = []
          fieldHeaderData3.push(JSON.parse(JSON.stringify(objKey3)));
          this.downloadExcel(res.zipFileName,resUsage,fieldHeaderData1,resDischarge,fieldHeaderData2,resTrans,fieldHeaderData3);
        }else{
          this._api.loaded();
          this.loaded = true;
          Swal.fire('', 'Report not found', 'warning');
        }
      });

    }
  }

  exportCSV = (): any => {
    this.dataSourceExport = [];
    this._api.loading();
    let diffYear = this.modelDate.endDate.getFullYear()-this.modelDate.startDate.getFullYear();
    let diffMonth = this.modelDate.endDate.getMonth()-this.modelDate.startDate.getMonth();
    if(diffYear > 1 || (diffYear ==1 && diffMonth>0)){
      this._api.loaded();
      this.loaded = true;
      Swal.fire('', 'Period cannot be longer than a year', 'warning')
    }else{
      let resDischarge = '';
      let resUsage = '';
      let resTrans = '';
      this.params.reportDate = new Date();
      this.params.userId = localStorage.getItem("userLogin")
      this.params.startDate = this.modelDate.startDate;
      this.params.endDate = this.modelDate.endDate
      this._api.getReportUsage(this.params,(res:any)=>{
        if(res.usageSummary.length > 0 || res.dischargeReport.length > 0){
          resUsage = res.usageSummary;
          resDischarge = res.dischargeReport;
          resTrans = res.transactionReport;
          this.dataSourceExport.push(resUsage);
          this.dataSourceExport.push(resDischarge);
          this.dataSourceExport.push(resTrans);
          var objKey1 = ['Report Date','Requested by','Report Period','Member code','Member Name','Product Code','Product','Usage']
          var objKey2 = ['Report Date','Requested by','Report Period','Member Code','Member Name','Report Order Date & time','HKBRC','HKCI','DUNS NO','Other Registration/Incorporation Number','Place of registration/Incorporation','Customer Number','Location/Branch ID','Account Manager Code','Reason Code','User ID','Product Code','Product Name','Report Ref.No','AI Reference Code 1','AI Reference Code 2','AI Reference Code 3','Discharge Date','Status']
          var objKey3 = ['Report Date','Requested by','Report Period','Member Code','Member Name','Report Order Date & time','HKBRC','HKCI','DUNS NO','Other Registration/Incorporation Number','Place of registration/Incorporation','Customer Number','Location/Branch ID','Account Manager Code','Reason Code','User ID','Product Code','Product Name','Report Ref.No','AI Reference Code 1','AI Reference Code 2','AI Reference Code 3']
          let fieldHeaderData1 = []
          fieldHeaderData1.push(JSON.parse(JSON.stringify(objKey1)));
          let fieldHeaderData2 = []
          fieldHeaderData2.push(JSON.parse(JSON.stringify(objKey2)));
          let fieldHeaderData3 = []
          fieldHeaderData3.push(JSON.parse(JSON.stringify(objKey3)));
          let sumCsv = {data:this.dataSourceExport[0],headers:objKey1,fileName:'Usage Summary'};
          let dischargeCsv = {data:this.dataSourceExport[1],headers:objKey2,fileName:'Discharge'};
          let usagetransCsv = {data:this.dataSourceExport[2],headers:objKey3,fileName:'Usage transaction'};
          this.exportAsCsvFile(res.zipFileName,sumCsv,usagetransCsv,dischargeCsv);
        }else{
          this._api.loaded();
          this.loaded = true;
          Swal.fire('', 'Report not found', 'warning');
        }
      });
    }
  }
  exportAsCsvFile(fileName:any,usageSum:any,usageTrans:any,discharge:any): void {
    this._api.loaded();
    this.loaded = true;
    if(usageSum!=undefined){
      if(usageSum.data.length > 0){
        let header = Object.keys(usageSum.data[0]);
        const replacer = (key:any, values:any) => values === null ? '' : values
        this.sumcsvdata = usageSum.data.map((row:any) => 
        header.map((fieldName:any) => JSON.stringify(row[fieldName], replacer)).join(','))
        header = usageSum.headers;
        this.sumcsvdata.unshift(header.join(','));
        this.sumcsvdata = this.sumcsvdata.join('\r\n');
      }else{
        let header = usageSum.headers;
        const replacer = (key:any, values:any) => values === null ? '' : values
        this.sumcsvdata = usageSum.data.map((row:any) => 
        header.map((fieldName:any) => JSON.stringify(row[fieldName], replacer)).join(','))
        this.sumcsvdata.unshift(header.join(','));
        this.sumcsvdata = this.sumcsvdata.join('\r\n');
      }
    }
    if(usageTrans!=undefined){
      if(usageTrans.data.length > 0){
        let header = Object.keys(usageTrans.data[0]);
        const replacer = (key:any, values:any) => values === null ? '' : values
        this.transcsvdata = usageTrans.data.map((row:any) => 
        header.map((fieldName:any) => JSON.stringify(row[fieldName], replacer)).join(','))
        header = usageTrans.headers;
        this.transcsvdata.unshift(header.join(','));
        this.transcsvdata = this.transcsvdata.join('\r\n');
      }else{
        let header = usageTrans.headers;
        const replacer = (key:any, values:any) => values === null ? '' : values
        this.transcsvdata = usageTrans.data.map((row:any) => 
        header.map((fieldName:any) => JSON.stringify(row[fieldName], replacer)).join(','))
        this.transcsvdata.unshift(header.join(','));
        this.transcsvdata = this.transcsvdata.join('\r\n');
      }
    }
    if(discharge!=undefined){
      if(discharge.data.length > 0){
        let header = Object.keys(discharge.data[0]);
        const replacer = (key:any, values:any) => values === null ? '' : values
        this.dischargecsvdata = discharge.data.map((row:any) => 
        header.map((fieldName:any) => JSON.stringify(row[fieldName], replacer)).join(','))
        header = discharge.headers;
        this.dischargecsvdata.unshift(header.join(','));
        this.dischargecsvdata = this.dischargecsvdata.join('\r\n');
      }else{
        let header = discharge.headers;
        const replacer = (key:any, values:any) => values === null ? '' : values
        this.dischargecsvdata = discharge.data.map((row:any) => 
        header.map((fieldName:any) => JSON.stringify(row[fieldName], replacer)).join(','))
        this.dischargecsvdata.unshift(header.join(','));
        this.dischargecsvdata = this.dischargecsvdata.join('\r\n');
      }
    }
    this.downloadZip(fileName,this.sumcsvdata,this.transcsvdata,this.dischargecsvdata);
  }

  downloadZip(fileName:any,csvData1:any,csvData2:any,csvData3:any)
  {      
    const zip = new JSZip();
    zip.file(fileName+"-1.csv", csvData1);
    zip.file(fileName+"-2.csv", csvData2);
    zip.file(fileName+"-3.csv", csvData3);

    zip.generateAsync({ type: 'blob' }).then((content) => {  
      if (content) {  
        FileSaver.saveAs(content, fileName+".zip");  
      }  
    }); 
  }
  downloadExcel(fileName:any,res1:any,header1:any,res2:any,header2:any,res3:any,header3:any) {
      var wb = XLSX.utils.book_new();
      var wsUsageSum = XLSX.utils.json_to_sheet(header1, {header: [], skipHeader: true});
                       XLSX.utils.sheet_add_json(wsUsageSum, res1, {skipHeader: true, origin: "A2"});
      XLSX.utils.book_append_sheet(wb, wsUsageSum, "Usage summary");
      
      var wsTrans = XLSX.utils.json_to_sheet(header3, {header: [], skipHeader: true}); 
                        XLSX.utils.sheet_add_json(wsTrans, res3, {skipHeader: true, origin: "A2"});
      XLSX.utils.book_append_sheet(wb, wsTrans, "Usage transaction");
      
      var wsDischarge = XLSX.utils.json_to_sheet(header2, {header: [], skipHeader: true}); 
                        XLSX.utils.sheet_add_json(wsDischarge, res2, {skipHeader: true, origin: "A2"});
      XLSX.utils.book_append_sheet(wb, wsDischarge, "Discharge");

      const fileType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
      const excelBuffer = XLSX.write(wb, { bookType: 'xlsx', type: 'array' });
      const data1 = new Blob([excelBuffer], { type: fileType });
      this._api.loaded();
      this.loaded = true;
      FileSaver.saveAs(data1, fileName+".xlsx");    
  }

}
