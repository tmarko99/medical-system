package it.engineering.entity;

public enum OrganizationType {
    HOSPITAL("Hospital"),
    INSURANCE_COMPANY("Insurance company"),
    EDUCATION_INSTITUTE("Education institute"),
    CLINICAL_RESEARCH("Clinical research"),
    OTHER("Other");

    private final String fullName;

    OrganizationType(String fullName) {
        this.fullName = fullName;
    }
}
