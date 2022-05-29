package it.engineering.repository;

import it.engineering.entity.Gender;
import it.engineering.entity.Practitioner;
import it.engineering.entity.Qualification;
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
public interface PractitionerRepository extends JpaRepository<Practitioner, Integer> {
    @Query("SELECT p FROM Practitioner p WHERE p.active=true AND p.organization.active=true")
    Page<Practitioner> findAll(Pageable pageable);

    List<Practitioner> findAllByOrganizationId(Integer id);

    @Query("SELECT p FROM Practitioner p WHERE p.active=true")
    List<Practitioner> findAll();

    @Query("SELECT p FROM Practitioner p WHERE p.active=true AND p.organization.active=true AND p.gender=:filter")
    Page<Practitioner> findAllByGender(@Param("filter") Gender filter, Pageable pageable);

    @Query("SELECT p FROM Practitioner p WHERE p.active=true AND p.organization.active=true AND p.qualification=:filter")
    Page<Practitioner> findAllByQualification(@Param("filter") Qualification filter, Pageable pageable);
    @Query("SELECT p FROM Practitioner p WHERE p.active=true AND p.organization.active=true AND (LOWER(p.name) LIKE %:filter% OR LOWER(p.surname) LIKE %:filter%)")
    Page<Practitioner> findAllByNameContainingAndSurnameContaining(@Param("filter") String filter, Pageable pageable);

    @Query("UPDATE Practitioner p SET p.active=false WHERE p.id=:id")
    @Modifying
    @Transactional
    void delete(@Param("id") Integer id);
}
