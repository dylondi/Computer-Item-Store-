package com.server.side;

import java.io.IOException;

import java.net.URISyntaxException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;
import java.util.stream.Collectors;

import com.server.side.components.ComputerComponent;
import com.server.side.inventory.Inventory;
import com.server.side.inventory.InventoryParser;
import com.server.side.inventory.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class AnalyserIntegrationTest {

    static Analyser analyser;
    static Map<UUID, ComputerComponent> expectedResult;
    static List<ComputerComponent> computerComponents;
    static Inventory inventory;

    @BeforeAll
    public static void setUp() throws IOException {

        Parser parser = new Parser("src/main/resources/Inventory.csv");
        List<String> parsedCSV = parser.readCSV();
        analyser = new Analyser();
        InventoryParser inventoryParser = new InventoryParser();
        InMemDbGenerator inMemDbGenerator = new InMemDbGenerator();
        expectedResult = inMemDbGenerator.createInMemDb(inventoryParser.createObjects(parsedCSV));
        inventory = new Inventory(expectedResult);
        computerComponents = new ArrayList<>(inventory.getParsedInventory().values());

    }

    @Test
    void test_perform_analysis() throws URISyntaxException, IOException {
        String actualResult = analyser.performAnalysis(inventory);
        String expectedResult = Files.lines(Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource("performAnalysis")).toURI())).collect(Collectors.joining("\n"));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_sort_and_print_method() throws IOException, URISyntaxException {
        String actualResult = analyser.sortAndPrint((computerComponents));
        String expectedResult = Files.lines(Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource("expectedResultOfSortAndPrint")).toURI())).collect(Collectors.joining("\n"));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_get_avg_price_method() {
        double actualResult = analyser.computeAvgPriceOfAllComponents((computerComponents));
        assertEquals(145.83, actualResult);
    }

    @Test
    void test_get_avg_price_of_cpu_components_method() {
        double actualResult = analyser.computeAvgPriceOfCPUComponents((computerComponents));
        assertEquals(92.43, actualResult);
    }

    @Test
    void test_method_returning_cheapest_object_details() {
        String actualResult = analyser.cheapestComponent((computerComponents));
        assertEquals("b5709966-76a6-48fb-ab72-18be9135230a\tMouse\tCaselogicEMS-800OpticalMouse\tCaseLogic\t2.2Wx0.5Lx4.4D\tBlack\t4\t45\n", actualResult);
    }

    @Test
    void test_print_most_expensive_of_each_category() throws URISyntaxException, IOException {
        String actualResult = analyser.getMostExpensiveOfEachCategory((computerComponents));
        String expectedResult = Files.lines(Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource("expectedResultPrintMostExpensiveOfEachCategory")).toURI())).collect(Collectors.joining("\n"));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_report_of_quantity_of_components_per_category() {
        String actualResult = analyser.reportOfQuantityOfComponentsByCategory(computerComponents);
        String expectedResult = "{Storage=82, Monitor=192, Mouse=312, Memory=141, Keyboard=285, CPU=1131, GPU=55}";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_report_of_quantity_of_components_per_category_and_brand() {
        String actualResult = analyser.reportOfQuantityOfComponentsByCategoryAndBrand(computerComponents);
        String expectedResult =
            "{Storage={WesternDigital=15, Kingston=27, Samsung=10, Seagate=30}, Monitor={ViewSonic=20, LG=45, Samsung=47, Asus=80}, Mouse={Apple=20, Cougar=40, Razer=52, Kingston=15, Microsoft=50, CaseLogic=135}, Memory={Team=40, Kingston=89, Patriot=12}, Keyboard={Apple=20, logitech=90, Microsoft=153, RAZER=22}, CPU={AMD=210, Intel=921}, GPU={NVIDIA=55}}";
        assertEquals(expectedResult, actualResult);
    }

}
