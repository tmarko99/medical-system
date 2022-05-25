package it.engineering.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MaritalStatus {
    ANNULLED("Annulled"),
    DIVORCED("Divorced"),
    MARRIED("Married"),
    POLYGAMOUS("Polygamous"),
    NEVER_MARRIED("Never married"),
    WIDOWED("Widowed");

    private static Map<String, MaritalStatus> FORMAT_MAP = Stream
            .of(MaritalStatus.values())
            .collect(Collectors.toMap(s -> s.fullName, Function.identity()));
    private final String fullName;

    MaritalStatus(String fullName) {
        this.fullName = fullName;
    }

    @JsonCreator // This is the factory method and must be static
    public static MaritalStatus fromString(String string) {
        return Optional.ofNullable(FORMAT_MAP.get(string)).orElseGet(() -> MaritalStatus.valueOf(string));
    }
}
