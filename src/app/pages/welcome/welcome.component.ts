import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  a:String ='s';
  constructor() { 
    console.log("Constructor WelcomeComponent");
  }

  ngOnInit(): void {
    console.log("INI WelcomeComponent");
  }

}
