package it.engineering.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum OrganizationType {
    HOSPITAL("Hospital"),
    INSURANCE_COMPANY("Insurance company"),
    EDUCATION_INSTITUTE("Education institute"),
    CLINICAL_RESEARCH("Clinical research"),
    OTHER("Other");


    private static Map<String, OrganizationType> FORMAT_MAP = Stream
            .of(OrganizationType.values())
            .collect(Collectors.toMap(s -> s.fullName, Function.identity()));
    private final String fullName;

    OrganizationType(String fullName) {
        this.fullName = fullName;
    }

    @JsonCreator // This is the factory method and must be static
    public static OrganizationType fromString(String string) {
        return Optional.ofNullable(FORMAT_MAP.get(string)).orElseGet(() -> OrganizationType.valueOf(string));
    }
}
