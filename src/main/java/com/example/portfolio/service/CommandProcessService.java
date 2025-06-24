package com.example.portfolio.service;

import com.example.portfolio.Enum.CommandType;
import com.example.portfolio.repository.FundRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Command Processor
 *  : process different command
 *   - CURRENT_PORTFOLIO
 *   - CALCULATE_OVERLAP
 *   - ADD_STOCK
 * @Date 18/06/2025
 * @author evie
 */
public class CommandProcessService {
    private FundRepository fundRepository;

    private PortfolioService portfolioService;

    public CommandProcessService(FundRepository fundRepository, PortfolioService portfolioService) {
        this.fundRepository = fundRepository;
        this.portfolioService = portfolioService;
    }


    public void processCommands(String commandFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(commandFilePath));

        for(String line : lines) {
            if(line.trim().isEmpty()) continue;

            String[] objects = line.split("\\s+");
            if(objects.length == 0) return;
            CommandType cmdType = CommandType.fromString(objects[0]);

            // process different commands
            switch (cmdType) {
                case CURRENT_PORTFOLIO:
                    processCurrentPortfolioCMD(objects);
                    break;
                case CALCULATE_OVERLAP:
                    processCurrentOverlap(objects);
                    break;
                case ADD_STOCK:
                    processAddStock(objects);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Process CMD CURRENT_PORTFOLIO
     * @param objects
     */
    private void processCurrentPortfolioCMD(String[] objects) {
        //check command length
        if(objects.length < CommandType.CURRENT_PORTFOLIO.getArgCount()) {
            System.err.println("CURRENT_PORTFOLIO command type should be CURRENT_PORTFOLIO FUND_NAME_1 FUND_NAME_2");
            throw new IllegalArgumentException("Invalid CURRENT_PORTFOLIO command format");
        }

        List<String> fundNames = new ArrayList<>();
        fundNames.addAll(Arrays.asList(objects).subList(1, objects.length));

        portfolioService.initialCurrentPortfolio(fundNames);
    }


    /**
     * Process CMD CALCULATE_OVERLAP
     * @param objects
     */
    private void processCurrentOverlap(String[] objects) {
        //check command length
        if(objects.length != CommandType.CALCULATE_OVERLAP.getArgCount()) {
            System.err.println("CALCULATE_OVERLAP command type should be: CALCULATE_OVERLAP FUND_NAME");
            throw new IllegalArgumentException("Invalid CALCULATE_OVERLAP command format");
        }

        String newFundName = objects[1];

        if(!fundRepository.getAllFundNames().contains(newFundName)) {
            System.out.println("FUND_NOT_FOUND");
            return;
        }

        List<String> results = portfolioService.calculateOverlap(newFundName);
        results.forEach(System.out::println);
    }


    /**
     * Process CMD ADD_STOCK
     * @param objects
     */
    private void processAddStock(String[] objects) {
        //check command length
        if(objects.length < CommandType.ADD_STOCK.getArgCount()) {
            System.err.println("ADD_STOCK command type should be: ADD_STOCK FUND_NAME STOCK_NAME");
            throw new IllegalArgumentException("Invalid ADD_STOCK command format");
        }

        String fundName = objects[1];
        if(!fundRepository.getAllFundNames().contains(fundName)) {
            System.out.println("FUND_NOT_FOUND");
            return;
        }

        //process stock name
        String stockName = buildStockName(objects);
        boolean processResult = portfolioService.addStock(fundName, stockName);
        if(!processResult) System.out.println("FUND_NOT_FOUND");
    }


    private String buildStockName(String[] objects) {
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i < objects.length; i++) {
            if(i > 2) {
                sb.append(" ");
            }
            sb.append(objects[i]);
        }
        return sb.toString();
    }

}
