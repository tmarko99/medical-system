package it.engineering.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Status {
    PLANNED("Planned"),
    TRIAGED("Triaged"),
    IN_PROGRESS("In progress"),
    SUSPENDED("Suspended"),
    FINISHED("Finished"),
    CANCELLED("Cancelled"),
    ENTERED_IN_ERROR("Entered in error");

    private static Map<String, Status> FORMAT_MAP = Stream
            .of(Status.values())
            .collect(Collectors.toMap(s -> s.fullName, Function.identity()));
    private final String fullName;

    Status(String fullName) {
        this.fullName = fullName;
    }

    @JsonCreator // This is the factory method and must be static
    public static Status fromString(String string) {
        return Optional.ofNullable(FORMAT_MAP.get(string)).orElseGet(() -> Status.valueOf(string));
    }
}
