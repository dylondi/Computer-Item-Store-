import { Injectable, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SseService } from './sse.service';

@Injectable({
  providedIn: 'root'
})
export class SearchServiceService {

  constructor(private http:HttpClient, private service:SseService, private zone: NgZone) { }

public getComponents(){
  return this.http.get("http://localhost:8080/list_items");
}

public searchComponent(name){
  return this.http.get("http://localhost:8080/search?name="+name);
}

public getNumOfItems(){
  return this.http.get<number>("http://localhost:8080/item_count");
}


public getOrderStatus(){

return Observable.create(observer => { const eventSource = this.service.getEventSource("http://localhost:8080/order_status");

eventSource.onmessage = event => { 
  this.zone.run(() => {
    observer.next(event)
  });
};

eventSource.onerror = error => {
  this.zone.run(() => {
    observer.error(error);
  });
};
});


}

}


