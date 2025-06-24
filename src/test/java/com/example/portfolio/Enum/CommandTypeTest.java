package com.example.portfolio.Enum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CommandTypeTest {

    @Test
    void fromString_validNames() {
        assertEquals(CommandType.CURRENT_PORTFOLIO, CommandType.fromString("CURRENT_PORTFOLIO"));
        assertEquals(CommandType.CALCULATE_OVERLAP, CommandType.fromString("calculate_overlap"));
        assertEquals(CommandType.ADD_STOCK, CommandType.fromString("ADD_STOCK"));
    }

    @Test
    void fromString_invalidName_throws() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> CommandType.fromString("UNKNOWN")
        );
        assertTrue(ex.getMessage().contains("Unknown command"));
    }

    @Test
    void getArgCount_and_getTypeName() {
        assertEquals(2, CommandType.CURRENT_PORTFOLIO.getArgCount());
        assertEquals("CURRENT_PORTFOLIO", CommandType.CURRENT_PORTFOLIO.getTypeName());
        assertEquals(2, CommandType.CALCULATE_OVERLAP.getArgCount());
        assertEquals(3, CommandType.ADD_STOCK.getArgCount());
    }
}
