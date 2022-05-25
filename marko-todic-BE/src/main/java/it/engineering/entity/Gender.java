package it.engineering.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other"),
    UNKNOWN("Unknown");

    private static Map<String, Gender> FORMAT_MAP = Stream
            .of(Gender.values())
            .collect(Collectors.toMap(s -> s.fullName, Function.identity()));
    private final String fullName;

    Gender(String fullName) {
        this.fullName = fullName;
    }

    @JsonCreator // This is the factory method and must be static
    public static Gender fromString(String string) {
        return Optional.ofNullable(FORMAT_MAP.get(string)).orElseGet(() -> Gender.valueOf(string));
    }
}
