package com.example.portfolio.Enum;

import java.util.Arrays;

/**
 * Commend Type
 */
public enum CommandType {
    /**
     * (typeName, command arguments number limit)
     */
    CURRENT_PORTFOLIO("CURRENT_PORTFOLIO", 2),
    CALCULATE_OVERLAP("CALCULATE_OVERLAP", 2),
    ADD_STOCK("ADD_STOCK", 3),
    ;

    private String typeName;
    private int argCount;

    CommandType(String typeName, int argCount) {
        this.typeName = typeName;
        this.argCount = argCount;
    }

    public int getArgCount() {
        return argCount;
    }

    public String getTypeName() {
        return typeName;
    }

    public static CommandType fromString(String name) {
        return Arrays.stream(values())
                .filter(c -> c.typeName.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown command: " + name));
    }
}
