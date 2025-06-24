package com.example.portfolio.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FundTest {
    @Test
    void getStockCount_and_addStock() {
        Set<String> stocks = new LinkedHashSet<>(Arrays.asList("A", "B"));
        Fund f = new Fund("F1", stocks);

        assertEquals(2, f.getStockCount());
        f.addStock("C");
        assertTrue(f.getStocks().contains("C"));
        assertEquals(3, f.getStockCount());
    }

    @Test
    void toString_containsNameAndCount() {
        Fund f = new Fund("XYZ", new LinkedHashSet<>(Arrays.asList("S1", "S2")));
        String txt = f.toString();
        assertTrue(txt.contains("name='XYZ'"));
        assertTrue(txt.contains("stockCount=2"));
    }
}
