package it.engineering.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Qualification {
    DOCTOR_OF_MEDICINE("Doctor of medicine"),
    MEDICAL_ASSISTANT("Medical assistant"),
    NURSE_PRACTITIONER("Nurse practitioner"),
    DOCTOR_OF_PHARMACY("Doctor of pharmacy"),
    CERTIFIED_NURSE_MIDWIFE("Certified nurse midwife"),
    EMERGENCY_MEDICAL_TECHNICIAN("Emergency medical technician");

    private static Map<String, Qualification> FORMAT_MAP = Stream
            .of(Qualification.values())
            .collect(Collectors.toMap(s -> s.fullName, Function.identity()));
    private final String fullName;

    Qualification(String fullName) {
        this.fullName = fullName;
    }

    @JsonCreator // This is the factory method and must be static
    public static Qualification fromString(String string) {
        return Optional.ofNullable(FORMAT_MAP.get(string)).orElseGet(() -> Qualification.valueOf(string));
    }
}
