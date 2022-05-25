package it.engineering.dto;

import it.engineering.entity.OrganizationType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationDto {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String identifier;
    @NotNull
    private Boolean active;
    @NotNull
    private OrganizationType type;
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String name;
    private String address;
    private String phone;
    @Email
    private String email;
    private Integer numberOfPractitioners;
    private Integer numberOfPatients;
    private Integer numberOfExaminations;

    public OrganizationDto() {
    }

    public OrganizationDto(Integer id, String identifier, Boolean active, OrganizationType type, String name, String address, String phone, String email, Integer numberOfPractitioners, Integer numberOfPatients, Integer numberOfExaminations) {
        this.id = id;
        this.identifier = identifier;
        this.active = active;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.numberOfPractitioners = numberOfPractitioners;
        this.numberOfPatients = numberOfPatients;
        this.numberOfExaminations = numberOfExaminations;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfPractitioners() {
        return numberOfPractitioners;
    }

    public void setNumberOfPractitioners(Integer numberOfPractitioners) {
        this.numberOfPractitioners = numberOfPractitioners;
    }

    public Integer getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(Integer numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public Integer getNumberOfExaminations() {
        return numberOfExaminations;
    }

    public void setNumberOfExaminations(Integer numberOfExaminations) {
        this.numberOfExaminations = numberOfExaminations;
    }
}
