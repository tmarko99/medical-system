package it.engineering.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.engineering.entity.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class ExaminationFullDto {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String identifier;
    @NotNull
    private Status status;
    @NotNull
    private ServiceType serviceType;
    @NotNull
    private Priority priority;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private Date endDate;
    @NotNull
    @NotEmpty
    private String diagnosis;
    private Organization organization;
    private List<PractitionerSimpleDto> practitioners;
    private PatientSimpleDto patient;

    public ExaminationFullDto() {
    }

    public ExaminationFullDto(Integer id, String identifier, Status status, ServiceType serviceType, Priority priority, Date startDate, Date endDate, String diagnosis, Organization organization, List<PractitionerSimpleDto> practitioners, PatientSimpleDto patient) {
        this.id = id;
        this.identifier = identifier;
        this.status = status;
        this.serviceType = serviceType;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.diagnosis = diagnosis;
        this.organization = organization;
        this.practitioners = practitioners;
        this.patient = patient;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<PractitionerSimpleDto> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(List<PractitionerSimpleDto> practitioners) {
        this.practitioners = practitioners;
    }

    public PatientSimpleDto getPatient() {
        return patient;
    }

    public void setPatient(PatientSimpleDto patient) {
        this.patient = patient;
    }
}
