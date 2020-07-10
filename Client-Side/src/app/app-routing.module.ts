import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { ItemCountComponent } from './item-count/item-count.component';
import { CartPageComponent } from './cart-page/cart-page.component';
import { OrderStatusComponent } from './order-status/order-status.component';
import { CheckoutComponent } from './checkout/checkout.component';


const routes: Routes = [
  {path:"", redirectTo:"list_items", pathMatch:"full"},
  {path:"list_items", component:SearchComponent},
  {path:"search", component:SearchComponent},
  {path:"item_count", component:ItemCountComponent},
  {path:"cart_page", component:CartPageComponent},
  {path:"order_status", component:OrderStatusComponent},
  {path:"checkout", component:CheckoutComponent}

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
