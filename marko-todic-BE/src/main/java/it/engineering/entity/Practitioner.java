package it.engineering.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"identifier"}) })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Practitioner {
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
    @Column(name = "birth_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String address;
    private String phone;
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
    @OneToMany(mappedBy = "practitioner")
    @JsonBackReference
    private List<Patient> patients = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "organization_id")
    @JsonManagedReference
    private Organization organization;
    @ManyToMany(mappedBy = "practitioners")
    @JsonManagedReference
    List<Examination> examinations = new ArrayList<>();

    public Practitioner() {
    }

    public Practitioner(Integer id, String identifier, Boolean active, String name, String surname, Gender gender, Date birthDate, String address, String phone, String email, Qualification qualification, List<Patient> patients, Organization organization, List<Examination> examinations) {
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
        this.patients = patients;
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

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
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
        Practitioner that = (Practitioner) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
