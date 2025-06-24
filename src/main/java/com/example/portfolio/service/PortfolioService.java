package com.example.portfolio.service;

import com.example.portfolio.model.Fund;
import com.example.portfolio.repository.FundRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @Description: Portfolio Service
 * @Date 17/06/2025
 * @author evie
 */
public class PortfolioService {

    private FundRepository fundRepository;

    private Set<String> currentPortfolio;

    public PortfolioService(FundRepository repository) {
        this.fundRepository = repository;
        this.currentPortfolio = new LinkedHashSet<>();
    }

    /**
     * initial current portfolio funds
     */
    public void initialCurrentPortfolio(List<String> fundNames) {
        currentPortfolio.clear();
        currentPortfolio.addAll(fundNames);
    }

    /**
     * COMMAND: CALCULATE_OVERLAP
     * @param fundName
     * @return List<String>
     */
    public List<String> calculateOverlap(String fundName) {
        if(!fundRepository.checkIfFundExist(fundName)) {
            return Arrays.asList("FUND_NOT_FOUND");
        }

        List<String> results = new ArrayList<>();
        Fund newFund = fundRepository.getFundByName(fundName);

        // calculate overlap with each current fund
        for(String curFundName : currentPortfolio) {
            if(!fundRepository.checkIfFundExist(curFundName)) {
                continue;
            }

            Fund curFund = fundRepository.getFundByName(curFundName);
            BigDecimal overlapPercentage = calOverlapPercentage(curFund, newFund);

            //print the overlapping percentage of stocks (if it greater than zero)
            if(overlapPercentage.compareTo(BigDecimal.ZERO) > 0) {
                results.add(fundName + " " + curFundName + " " + overlapPercentage + "%");
            }
        }
        return results;
    }

    /**
     * COMMAND: ADD_STOCK
     * @param fundName
     * @param stockName
     * @return boolean
     */
    public boolean addStock(String fundName, String stockName) {
        if(!fundRepository.checkIfFundExist(fundName)) return false;

        return fundRepository.addStockToFund(fundName, stockName);
    }

    /**
     * calculate the overlap percentage between two funds
     */
    private BigDecimal calOverlapPercentage(Fund f1, Fund f2) {
        int commonStocks = calCommonStockBetweenTwoFunds(f1, f2);
        int totalStocks = f1.getStockCount() + f2.getStockCount();
        if(totalStocks == 0) return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        BigDecimal numerator = BigDecimal.valueOf(commonStocks).multiply(BigDecimal.valueOf(2));
        BigDecimal fraction = numerator.divide(
                BigDecimal.valueOf(totalStocks),
                4,
                RoundingMode.HALF_UP);

        BigDecimal result = fraction.multiply(BigDecimal.valueOf(100));
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * calculate common stocks number between two funds
     */
    private int calCommonStockBetweenTwoFunds(Fund f1, Fund f2) {
        Set<String> commonStocks = new HashSet<>(f1.getStocks());
        commonStocks.retainAll(f2.getStocks());
        return commonStocks.size();
    }

}
