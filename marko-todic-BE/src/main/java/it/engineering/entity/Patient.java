package it.engineering.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"identifier"}) })
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    @Column(nullable = false)
    private Boolean active;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    private String address;
    private String phone;
    private String email;
    private Boolean deceased;
    @Enumerated(EnumType.STRING)
    @Column(name = "marital_Status")
    private MartialStatus maritalStatus;
    @ManyToOne
    @JoinColumn(name = "practitioner_id", nullable = false)
    private Practitioner practitioner;
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    @OneToMany(mappedBy = "patient")
    private List<Examination> examinations;

    public Patient() {
    }

    public Patient(Integer id, String identifier, Boolean active, String name, String surname, Gender gender, Date birthDate, String address, String phone, String email, Boolean deceased, MartialStatus maritalStatus, Practitioner practitioner, Organization organization, List<Examination> examinations) {
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

    public MartialStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MartialStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(Practitioner practitioner) {
        this.practitioner = practitioner;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Patient patient = (Patient) o;
        return id != null && Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
