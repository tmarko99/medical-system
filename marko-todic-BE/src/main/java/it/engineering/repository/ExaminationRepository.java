package it.engineering.repository;

import it.engineering.entity.Examination;
import it.engineering.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Integer> {
    @Query(value = "SELECT e FROM Examination e WHERE e.status <> 'ENTERED_IN_ERROR'")
    Page<Examination> findAll(Pageable pageable);
    @Query("UPDATE Examination e SET e.status='ENTERED_IN_ERROR' WHERE e.id=:id")
    @Modifying
    @Transactional
    void delete(@Param("id") Integer id);
}
