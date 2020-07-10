import { Injectable } from '@angular/core';
import { ComputerComponent } from './ComputerComponent';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Order } from './order';


@Injectable({
  providedIn: 'root'
})
export class CartService {


  items: ComputerComponent[] = [];
   totalPrice:number = 0;
   myUrl = "http://localhost:8080/checkout";
   httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { 
  }

  public addItemToCart(component:ComputerComponent){
  
    let flag: boolean = false;
    let iter:number = 0;
    
    if(this.items.length>0){
      for(let i=0; i<this.items.length; i++){
          if(this.items[i].uuid==component.uuid){
             flag = true;
              iter = i;
          }
      }

      if(flag&&this.items[iter].numInBasket<this.items[iter].quantity){
          this.items[iter].numInBasket+=1;
          this.totalPrice+=component.price;
      }else if(!flag){
          component.numInBasket = 1;
          this.items.push(component);
          this.totalPrice+=component.price;
      }else{
        alert("We dont have any more of these in stock!");
      }

    }else{
        component.numInBasket = 1;
        this.items.push(component);
        this.totalPrice+=component.price;
    }
    console.log(this.totalPrice);
  }

  public removeItemFromCart(component:ComputerComponent){
    for(let i=0; i<this.items.length; i++){
      if(this.items[i].uuid==component.uuid){
        this.totalPrice -= this.items[i].price;
        if(this.items[i].numInBasket>1){
          this.items[i].numInBasket -= 1;
        }
        else{
          this.items.splice(i, 1);
        }
      }
    }
  }

  public getTotalPrice():number{
    return this.totalPrice;
  }


  public sendOrder(orders: Order[]): Observable<String> {
    return this.http.post<String>(this.myUrl , orders, this.httpOptions);
   }

}
