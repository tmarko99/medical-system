package it.engineering.dto;

import it.engineering.entity.Qualification;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PractitionerIdentifierNameDto {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String identifier;
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String name;

    @NotNull
    private Qualification qualification;

    public PractitionerIdentifierNameDto() {
    }

    public PractitionerIdentifierNameDto(Integer id, String identifier, String name, Qualification qualification) {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
        this.qualification = qualification;
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

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }
}
