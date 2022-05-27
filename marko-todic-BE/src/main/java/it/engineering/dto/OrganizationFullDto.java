package it.engineering.dto;

import it.engineering.entity.OrganizationType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class OrganizationFullDto {
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
    private List<PractitionerSimpleDto> practitioners;
    private List<PatientSimpleDto> patients;
    private List<ExaminationFullDto> examinations;

    public OrganizationFullDto() {
    }

    public OrganizationFullDto(Integer id, String identifier, Boolean active, OrganizationType type, String name, String address, String phone, String email, List<PractitionerSimpleDto> practitioners, List<PatientSimpleDto> patients, List<ExaminationFullDto> examinations) {
        this.id = id;
        this.identifier = identifier;
        this.active = active;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.practitioners = practitioners;
        this.patients = patients;
        this.examinations = examinations;
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

    public List<PractitionerSimpleDto> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(List<PractitionerSimpleDto> practitioners) {
        this.practitioners = practitioners;
    }

    public List<PatientSimpleDto> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientSimpleDto> patients) {
        this.patients = patients;
    }

    public List<ExaminationFullDto> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<ExaminationFullDto> examinations) {
        this.examinations = examinations;
    }
}
