package it.engineering.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"identifier", "name"}) })
// this annotation help with the serialization of entities with bidirectional relationships
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    @Column(nullable = false)
    private Boolean active;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrganizationType type;
    @Column(nullable = false)
    private String name;
    private String address;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "organization")
    @JsonBackReference
    private List<Practitioner> practitioners = new ArrayList<>();
    @OneToMany(mappedBy = "organization")
    @JsonBackReference
    private List<Patient> patients = new ArrayList<>();
    @OneToMany(mappedBy = "organization")
    //this annotation is used to prevent infinite-recursion
    @JsonBackReference
    private List<Examination> examinations = new ArrayList<>();

    public Organization() {
    }

    public Organization(Integer id, String identifier, Boolean active, OrganizationType type, String name, String address, String phone, String email, List<Practitioner> practitioners, List<Patient> patients, List<Examination> examinations) {
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

    public List<Practitioner> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(List<Practitioner> practitioners) {
        this.practitioners = practitioners;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
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
        Organization that = (Organization) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
