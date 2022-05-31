package it.engineering.dto;

import it.engineering.entity.Priority;
import it.engineering.entity.Status;

public class FilterDto {
    private Integer organization;
    private Integer patient;
    private Integer serviceType;
    private Status status;
    private Priority priority;


    public FilterDto() {
    }

    public FilterDto(Integer organization, Integer patient, Integer serviceType, Status status, Priority priority) {
        this.organization = organization;
        this.patient = patient;
        this.serviceType = serviceType;
        this.status = status;
        this.priority = priority;
    }

    public Integer getOrganization() {
        return organization;
    }

    public void setOrganization(Integer organization) {
        this.organization = organization;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
