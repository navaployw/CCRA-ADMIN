import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CardListComponent } from './card-list.component';

@NgModule({
  declarations: [CardListComponent],
  imports: [
    CommonModule,
  	FormsModule,
  	ReactiveFormsModule,
  ],
  exports:[
  	CardListComponent,
    FormsModule
  ]
})
export class CardListModule { }
