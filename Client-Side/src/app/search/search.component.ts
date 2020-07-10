import { Component, OnInit } from '@angular/core';
import { SearchServiceService } from '../search-service.service';
import { ComputerComponent } from '../ComputerComponent';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  computerComponents:any;
  name:string;
  numOfItems:number;
  
  constructor(private service:SearchServiceService, private service2:CartService) { }

  isShow = true;
  public findComponent(){
    let resp = this.service.searchComponent(this.name);
    if(this.name.length>=0){
      this.isShow = true;
    resp.subscribe((data)=>this.computerComponents=data);
    }
  }

  ngOnInit() {
    let resp = this.service.getComponents();
    resp.subscribe((data)=>this.computerComponents=data);
  }


 
  toggleDisplay() {
    this.isShow = !this.isShow;
  }

  public reloadButton(){
    location.reload();
  }

  addToCart(component:ComputerComponent){
    this.service2.addItemToCart(component);
  }
}
