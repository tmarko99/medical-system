package it.engineering.dto;

public class PatientIdentifierNameDto {
    private Integer id;

    private String identifier;
    private String name;

    public PatientIdentifierNameDto() {
    }

    public PatientIdentifierNameDto(Integer id, String identifier, String name) {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
