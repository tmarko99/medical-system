package it.engineering.dto;

import it.engineering.entity.OrganizationType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationIdentifierNameDto {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String identifier;
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String name;

    public OrganizationIdentifierNameDto() {
    }

    public OrganizationIdentifierNameDto(Integer id, String identifier, String name) {
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
