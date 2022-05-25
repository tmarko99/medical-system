package it.engineering.repository;

import it.engineering.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p FROM Patient p WHERE p.active=true")
    Page<Patient> findAll(Pageable pageable);
    @Query("UPDATE Patient p SET p.active=false WHERE p.id=:id")
    @Modifying
    @Transactional
    void delete(@Param("id") Integer id);
}
