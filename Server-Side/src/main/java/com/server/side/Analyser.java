package com.server.side;

import java.util.*;
import java.util.stream.Collectors;

import com.server.side.components.ComputerComponent;
import com.server.side.inventory.Inventory;


public class Analyser {

    public Analyser() {
    }

    public String performAnalysis(Inventory inventory) {

        List<ComputerComponent> computerComponents = new ArrayList<>(inventory.getParsedInventory().values());
        String returned = sortAndPrint(computerComponents) + "Average price of all computer components: " + computeAvgPriceOfAllComponents(computerComponents) + "\n" +
            "Average price of all CPU components: " + computeAvgPriceOfCPUComponents(computerComponents) + "\n" +
            "Details of cheapest component: " + cheapestComponent(computerComponents) + "\n" + getMostExpensiveOfEachCategory(computerComponents) + "\n" + reportOfQuantityOfComponentsByCategory(computerComponents) + "\n" +
            reportOfQuantityOfComponentsByCategoryAndBrand(computerComponents);
        System.out.println(returned);
        return returned;
    }

    String sortAndPrint(List<ComputerComponent> computerComponents) {
        List<ComputerComponent> firstTenOfSortedList = computerComponents.stream().sorted(Comparator.comparing(ComputerComponent::getBrand)).sorted(Comparator.comparing(ComputerComponent::getName)).sorted(Comparator
            .comparing(ComputerComponent::getCategory)).limit(10).collect(Collectors.toList());
        return ("Sorted by category, name and brand. Following components are the first 10 of this sorted list.\n" + firstTenOfSortedList);
    }

    double computeAvgPriceOfAllComponents(List<ComputerComponent> computerComponents) {
        return Math.round(computerComponents.stream().map(c -> c.getPrice()).mapToDouble(num -> num).sum() / computerComponents.size() * 100.0) / 100.0;
    }

    double computeAvgPriceOfCPUComponents(List<ComputerComponent> computerComponents) {

        double totalPriceOfCPUComponents = computerComponents.stream()
            .filter(name -> name.getCategory().equals("CPU"))
            .map(ComputerComponent::getPrice)
            .mapToDouble(num -> num).sum();

        double totalNumOfAllCPUComponents = (double) computerComponents.stream()
            .filter(name -> name.getCategory().equals("CPU"))
            .count();

        return Math.round((totalPriceOfCPUComponents / totalNumOfAllCPUComponents) * 100.0) / 100.0;
    }

    String cheapestComponent(List<ComputerComponent> computerComponents) {
        List<ComputerComponent> cheapestComponentObj = computerComponents.stream().sorted(Comparator.comparing(ComputerComponent::getPrice)).collect(Collectors.toList());
        return cheapestComponentObj.get(0).toString();
    }

    String getMostExpensiveOfEachCategory(List<ComputerComponent> computerComponents) {
        return computerComponents.stream().collect(Collectors.groupingBy(ComputerComponent::getCategory, Collectors.maxBy(Comparator.comparingInt(ComputerComponent::getPrice)))).toString();
    }

    String reportOfQuantityOfComponentsByCategory(List<ComputerComponent> computerComponents) {
        return computerComponents.stream().collect(Collectors.groupingBy(ComputerComponent::getCategory, Collectors.summingInt(ComputerComponent::getQuantity))).toString();
    }

    String reportOfQuantityOfComponentsByCategoryAndBrand(List<ComputerComponent> computerComponents) {
        return computerComponents.stream().collect(Collectors.groupingBy(ComputerComponent::getCategory, Collectors.groupingBy(ComputerComponent::getBrand, Collectors.summingInt(ComputerComponent::getQuantity)))).toString();
    }
}
