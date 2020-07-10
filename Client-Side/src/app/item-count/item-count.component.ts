import { Component, OnInit } from '@angular/core';
import { SearchServiceService } from '../search-service.service';

@Component({
  selector: 'app-item-count',
  templateUrl: './item-count.component.html',
  styleUrls: ['./item-count.component.css']
})
export class ItemCountComponent implements OnInit {

  numOfItems:number;

  constructor(private service:SearchServiceService) { }

  ngOnInit(){
    this.getItemCount();
  }

  public getItemCount(){
    let resp = this.service.getNumOfItems();
    resp.subscribe((data)=>this.numOfItems=data);
  }
  
  public reloadButton(){
    location.reload();
  }
}

