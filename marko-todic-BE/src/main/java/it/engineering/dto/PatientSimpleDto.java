package it.engineering.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.engineering.entity.*;

import javax.validation.constraints.*;
import java.util.Date;

public class PatientSimpleDto {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String identifier;
    @NotNull
    private Boolean active;
    @NotNull
    @NotEmpty
    @Size(min = 3, message = "Minimal number of characters is 3")
    private String name;
    @NotNull
    @NotEmpty
    @Size(min = 3, message = "Minimal number of characters is 3")
    private String surname;
    @NotNull
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past
    private Date birthDate;
    private String address;
    private String phone;
    @Email
    private String email;
    private Boolean deceased = false;
    @NotNull
    private MaritalStatus maritalStatus;
    private Integer practitioner;
    private Integer organization;

    public PatientSimpleDto() {
    }

    public PatientSimpleDto(Integer id, String identifier, Boolean active, String name, String surname, Gender gender, Date birthDate, String address, String phone, String email, Boolean deceased, MaritalStatus maritalStatus, Integer practitioner, Integer organization) {
        this.id = id;
        this.identifier = identifier;
        this.active = active;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.deceased = deceased;
        this.maritalStatus = maritalStatus;
        this.practitioner = practitioner;
        this.organization = organization;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public Boolean getDeceased() {
        return deceased;
    }

    public void setDeceased(Boolean deceased) {
        this.deceased = deceased;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(Integer practitioner) {
        this.practitioner = practitioner;
    }

    public Integer getOrganization() {
        return organization;
    }

    public void setOrganization(Integer organization) {
        this.organization = organization;
    }
}
