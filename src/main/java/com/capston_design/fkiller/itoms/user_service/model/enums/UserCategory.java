package com.capston_design.fkiller.itoms.user_service.model.enums;

import java.util.Arrays;

public enum UserCategory {
    REQUESTER,
    CREATOR,
    CHARGER;

    public static UserCategory from(String value) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown category: " + value));
    }
}
