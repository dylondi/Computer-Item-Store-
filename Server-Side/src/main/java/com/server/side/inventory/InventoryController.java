package com.server.side.inventory;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static com.server.side.Main.inventory;
import com.server.side.components.ComputerComponent;
import com.server.side.orders.Order;
import com.server.side.orders.OrderManager;
import com.server.side.persistence.Reader;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RestController
public class InventoryController {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }

    @GetMapping("/item_count")
    public int returnNumberOfItems() {
        Map<UUID, ComputerComponent> map = inventory.getParsedInventory();
        Reader reader = new Reader();
        reader.getNumOfItems(map);
        return reader.getNumOfItems(map);
    }

    @GetMapping("/search")
    public List<ComputerComponent> searchForComponent(@RequestParam(value = "name") String name) {
        Reader reader = new Reader();
        ComputerComponent cc = reader.findComponent(inventory.getParsedInventory(), name);
        List<ComputerComponent> list = new ArrayList<>();
        ;
        list.add(cc);
        return sortForGUI(list);
    }

    @GetMapping("/list_items")
    public List<ComputerComponent> listAllComponents() {
        List<ComputerComponent> list = new ArrayList<>(inventory.getParsedInventory().values());
        return sortForGUI(list);
    }

    public List<ComputerComponent> sortForGUI(List<ComputerComponent> list) {
        return list.stream()
                .sorted(Comparator.comparing(ComputerComponent::getBrand))
                .sorted(Comparator.comparing(ComputerComponent::getName))
                .sorted(Comparator.comparing(ComputerComponent::getCategory))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/checkout")
    public ResponseEntity getOrder(@Valid @RequestBody Order[] orders) {
        System.out.println("Order received");
        OrderManager orderManager = new OrderManager(inventory);
        if (orderManager.checkStock(orders)) {
            orderManager.updateInventory(orders);
        }
        return ResponseEntity.ok().body(orders);
    }

}
