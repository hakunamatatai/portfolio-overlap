package com.example.portfolio.repository;

import com.example.portfolio.model.Fund;
import org.junit.jupiter.api.Test;
import java.net.URL;;
import static org.junit.jupiter.api.Assertions.*;


public class FundRepositoryTest {

    @Test
    void loadFromClasspath_success() {
        URL json = getClass().getClassLoader().getResource("stock_data.json");
        assertNotNull(json, "you need to put stock_data.json into src/main/resources");
        FundRepository repo = new FundRepository(json);
        // two funds
        assertTrue(repo.checkIfFundExist("AXIS_BLUECHIP"));
        assertTrue(repo.checkIfFundExist("ICICI_PRU_BLUECHIP"));

        // check empty
        Fund f = repo.getFundByName("AXIS_BLUECHIP");
        assertNotNull(f);
        assertTrue(f.getStocks().size() > 0);
    }

    @Test
    void addStockToExistingFund() {
        URL json = getClass().getClassLoader().getResource("stock_data.json");
        FundRepository repo = new FundRepository(json);
        boolean added = repo.addStockToFund("AXIS_BLUECHIP", "MY_NEW_STOCK");
        assertTrue(added);
        assertTrue(repo.getFundByName("AXIS_BLUECHIP").getStocks().contains("MY_NEW_STOCK"));
    }

    @Test
    void addStockToNonexistentFund_returnsFalse() {
        URL json = getClass().getClassLoader().getResource("stock_data.json");
        FundRepository repo = new FundRepository(json);
        assertFalse(repo.addStockToFund("NON_EXISTENT", "STOCK"));
    }

}
