import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { ComputerComponent } from '../ComputerComponent';
import { Order } from '../order';
import { SearchServiceService } from '../search-service.service';


@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent implements OnInit {

  

  constructor(private service:CartService) { }


  items = this.service.items;
  ngOnInit(): void {
  }

public collectOrder(){
var cart = [];
for(var item of this.items){
  var order = new Order();
  order.uuid = item.uuid;
  order.category = item.category;
  order.price = item.price;
  order.quantity = item.numInBasket;
  cart.push(order);
}
this.service.sendOrder(cart).subscribe(data => {
  console.log(data);
  this.clearCart();
});
}

  removeFromCart(component:ComputerComponent){
    this.service.removeItemFromCart(component);
  }

  public getTotal():number{
        return this.service.totalPrice;
  }

  addToCart(component:ComputerComponent){
    this.service.addItemToCart(component);
  }

  clearCart(){
this.service.items = [];
this.items = [];
  }

}
