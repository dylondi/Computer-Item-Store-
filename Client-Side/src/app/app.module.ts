import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchComponent } from './search/search.component';
import { HttpClientModule} from '@angular/common/http';
import { FormsModule} from '@angular/forms';
import { SearchServiceService } from './search-service.service';
import { ItemCountComponent } from './item-count/item-count.component';
import { CartPageComponent } from './cart-page/cart-page.component';
import { OrderStatusComponent } from './order-status/order-status.component';
import { CheckoutComponent } from './checkout/checkout.component';


@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    ItemCountComponent,
    CartPageComponent,
    OrderStatusComponent,
    CheckoutComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [SearchServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
