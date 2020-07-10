package com.server.side.orders;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class OrderEventListener implements ApplicationListener<OrderEvent> {

    @Override
    public void onApplicationEvent(OrderEvent orderEvent) {
        System.out.println("Received spring custom event - " + orderEvent.getMessage());
    }
}
