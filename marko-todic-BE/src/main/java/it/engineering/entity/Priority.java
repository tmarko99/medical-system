package it.engineering.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Priority {
    ASAP("Asap"),
    CALLBACK_RESULTS("Callback results"),
    EMERGENCY("Emergency"),
    ROUTINE("Routine"),
    RUSH_REPORT("Rush report"),
    TIMING_CRITICAL("Timing critical");

    private static Map<String, Priority> FORMAT_MAP = Stream
            .of(Priority.values())
            .collect(Collectors.toMap(s -> s.fullName, Function.identity()));
    private final String fullName;

    Priority(String fullName) {
        this.fullName = fullName;
    }

    @JsonCreator // This is the factory method and must be static
    public static Priority fromString(String string) {
        return Optional.ofNullable(FORMAT_MAP.get(string)).orElseGet(() -> Priority.valueOf(string));
    }
}
