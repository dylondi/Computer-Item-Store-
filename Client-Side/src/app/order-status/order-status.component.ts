import { Component, OnInit } from '@angular/core';
import { SearchServiceService } from '../search-service.service';

@Component({
  selector: 'app-order-status',
  templateUrl: './order-status.component.html',
  styleUrls: ['./order-status.component.css']
})
export class OrderStatusComponent implements OnInit {


  message:string;

  constructor(private service:SearchServiceService) { }

  ngOnInit(){
this.service.getOrderStatus().subscribe(data => this.message = data.data);
console.log(this.message);

}



}
