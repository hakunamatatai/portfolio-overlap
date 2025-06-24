package com.example.portfolio.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataLoadExceptionTest {

    @Test
    void messageAndCause_propogated() {
        Throwable cause = new RuntimeException("root");
        DataLoadException ex = new DataLoadException("oops", cause);
        assertEquals("oops", ex.getMessage());
        assertSame(cause, ex.getCause());
    }
}
