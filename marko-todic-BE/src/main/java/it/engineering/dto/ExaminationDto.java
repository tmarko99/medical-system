package it.engineering.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.engineering.entity.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExaminationDto {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Minimal number of characters is 5")
    private String identifier;
    @NotNull
    private Status status;
    @NotNull
    private Integer serviceType;
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
    private Integer organization;
    private List<Integer> practitioners;
    private Integer patient;

    public ExaminationDto() {
    }

    public ExaminationDto(Integer id, String identifier, Status status, Integer serviceType, Priority priority, Date startDate, Date endDate, String diagnosis, Integer organization, List<Integer> practitioners, Integer patient) {
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

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
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

    public Integer getOrganization() {
        return organization;
    }

    public void setOrganization(Integer organization) {
        this.organization = organization;
    }

    public List<Integer> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(List<Integer> practitioners) {
        this.practitioners = practitioners;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }
}
