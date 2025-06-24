package com.example.portfolio.service;

import com.example.portfolio.repository.FundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class PortfolioServiceTest {

    private PortfolioService service;

    @BeforeEach
    void setup() throws Exception {
        URL json = getClass().getClassLoader().getResource("stock_data.json");
        FundRepository repo = new FundRepository(json);
        service = new PortfolioService(repo);
        // initialize two funds
        service.initialCurrentPortfolio(Arrays.asList("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP"));

    }

    @Test
    void calculateOverlap_knownFunds_returnsPercentages() {
        List<String> out = service.calculateOverlap("MIRAE_ASSET_EMERGING_BLUECHIP");
        assertEquals(2, out.size());

        // check format
        for (String line : out) {
            assertTrue(line.startsWith("MIRAE_ASSET_EMERGING_BLUECHIP "));
            assertTrue(line.matches(".* \\d+\\.\\d{2}%"),
                    "Should retain two decimal places and include a percent sign：" + line);
        }
    }

    @Test
    void calculateOverlap_nonexistentFund_returnsFundNotFound() {
        List<String> out = service.calculateOverlap("DOES_NOT_EXIST");
        assertEquals(1, out.size());
        assertEquals("FUND_NOT_FOUND", out.get(0));
    }

    @Test
    void addStock_existingFund_returnsTrue() {
        assertTrue(service.addStock("AXIS_BLUECHIP", "TEST_STOCK"));
    }

    @Test
    void addStock_nonexistentFund_returnsFalse() {
        assertFalse(service.addStock("NONE", "X"));
    }

}
