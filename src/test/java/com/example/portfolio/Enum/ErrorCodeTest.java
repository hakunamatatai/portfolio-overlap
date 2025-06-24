package com.example.portfolio.Enum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorCodeTest {
    @Test
    void codes_and_descriptions() {
        assertEquals(1, ErrorCode.USAGE.getCode());
        assertEquals("Usage error", ErrorCode.USAGE.getDescription());
        assertEquals(2, ErrorCode.RESOURCE_NOT_FOUND.getCode());
        assertEquals("Resource not found", ErrorCode.RESOURCE_NOT_FOUND.getDescription());
        assertEquals(3, ErrorCode.DATA_LOAD_ERROR.getCode());
        assertEquals("Data load error", ErrorCode.DATA_LOAD_ERROR.getDescription());
        assertEquals(4, ErrorCode.ERROR.getCode());
        assertEquals("Runtime exception", ErrorCode.ERROR.getDescription());
    }
}
