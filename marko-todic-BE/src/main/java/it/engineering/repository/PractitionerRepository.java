package it.engineering.repository;

import it.engineering.entity.Practitioner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, Integer> {
    @Query("SELECT p FROM Practitioner p WHERE p.active=true")
    Page<Practitioner> findAll(Pageable pageable);

    @Query("UPDATE Practitioner p SET p.active=false WHERE p.id=:id")
    @Modifying
    @Transactional
    void delete(@Param("id") Integer id);
}
