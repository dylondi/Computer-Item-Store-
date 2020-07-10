package com.server.side.inventory;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

import com.server.side.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Parser {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static String filePath;

    public Parser(String filePath) {
        this.filePath = filePath;
    }

    public static List<String> readCSV() throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static List<String> replaceCommasAndLog(List<String> commaSeparatedRecords) {
        List<String> tabSeparatedRecords = new ArrayList<>(commaSeparatedRecords.size());
        for (String line : commaSeparatedRecords) {
            tabSeparatedRecords.add(line.replace(",", "\t|\t"));
        }
        for (String line : tabSeparatedRecords) {
            LOGGER.info(line);
        }

        return tabSeparatedRecords;
    }

}
