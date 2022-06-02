package it.engineering.repository;

import it.engineering.dto.PatientSimpleDto;
import it.engineering.entity.MaritalStatus;
import it.engineering.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p FROM Patient p WHERE p.active=true AND p.organization.active=true")
    Page<Patient> findAll(Pageable pageable);

    List<Patient> findAllByOrganizationId(Integer id);

    @Query("SELECT p FROM Patient p WHERE p.active=true")
    List<Patient> findAll();
    @Query("SELECT p FROM Patient p WHERE p.active=true AND p.organization.active=true AND (LOWER(p.name) LIKE %:filter% OR LOWER(p.surname) LIKE %:filter%)")
    Page<Patient> findAllByName(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.active=true AND p.organization.active=true AND p.maritalStatus=:filter")
    Page<Patient> findByMaritalStatus(@Param("filter") MaritalStatus filter, Pageable pageable);
    @Query("UPDATE Patient p SET p.active=false WHERE p.id=:id")
    @Modifying
    @Transactional
    void delete(@Param("id") Integer id);
}
