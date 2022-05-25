package it.engineering.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.engineering.entity.Gender;
import it.engineering.entity.Qualification;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class PractitionerSimpleDto {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String identifier;
    @NotNull
    private Boolean active;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 3")
    private String name;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 3")
    private String surname;
    @NotNull
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @NotEmpty
    @Past
    private Date birthDate;
    private String address;
    private String phone;
    private String email;
    @NotNull
    private Qualification qualification;
    private Integer numberOfPatients;
    private Integer organization;

    public PractitionerSimpleDto() {
    }

    public PractitionerSimpleDto(Integer id, String identifier, Boolean active, String name, String surname, Gender gender, Date birthDate, String address, String phone, String email, Qualification qualification, Integer numberOfPatients, Integer organization) {
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
        this.qualification = qualification;
        this.numberOfPatients = numberOfPatients;
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

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Integer getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(Integer numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public Integer getOrganization() {
        return organization;
    }

    public void setOrganization(Integer organization) {
        this.organization = organization;
    }
}
