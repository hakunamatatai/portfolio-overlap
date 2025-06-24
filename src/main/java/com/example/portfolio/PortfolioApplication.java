package com.example.portfolio;

import com.example.portfolio.Enum.ErrorCode;
import com.example.portfolio.exception.DataLoadException;
import com.example.portfolio.repository.FundRepository;
import com.example.portfolio.service.CommandProcessService;
import com.example.portfolio.service.PortfolioService;
import java.net.URL;
import java.util.Objects;

/**
 * @Description: Portfolio Application Service
 * @Date 18/06/2025
 * @author evie
 */
public class PortfolioApplication {

    public static int runPortfolio(String[] input) {
        if (input.length < 1) {
            System.err.println("Usage: java -jar geektrust.jar <sample_input/input1.txt>");
            return ErrorCode.USAGE.getCode();
        }

        URL jsonUrl = PortfolioApplication.class.getClassLoader().getResource("stock_data.json");
        if(Objects.isNull(jsonUrl)) {
            System.err.println("Error: stock_data.json not found in resources");
            return ErrorCode.RESOURCE_NOT_FOUND.getCode();
        }

        try {
            // Set up the dependencies
            FundRepository fundRepository = new FundRepository(jsonUrl);
            PortfolioService portfolioService = new PortfolioService(fundRepository);
            CommandProcessService cmdProcess = new CommandProcessService(fundRepository, portfolioService);

            System.out.println();
            cmdProcess.processCommands(input[0]);
            return 0;

        } catch (DataLoadException e) {
            System.err.println("Data Load Error: " + e.getMessage());
            return ErrorCode.DATA_LOAD_ERROR.getCode();
        } catch (Exception e) {
            System.err.println("General Error: " + e.getMessage());
            return ErrorCode.ERROR.getCode();
        }
    }
}
