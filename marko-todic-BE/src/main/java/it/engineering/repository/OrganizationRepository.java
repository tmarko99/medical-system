package it.engineering.repository;

import it.engineering.entity.Organization;
import it.engineering.entity.OrganizationType;
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
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    @Query("SELECT o FROM Organization o WHERE o.active=true")
    Page<Organization> findAll(Pageable pageable);
    @Query("SELECT o FROM Organization o WHERE o.active=true AND o.type=:filter")
    Page<Organization> findAllByType(@Param("filter") OrganizationType filter, Pageable pageable);

    @Query("SELECT o FROM Organization o WHERE o.active=true AND LOWER(o.name) LIKE %:filter%")
    Page<Organization> findAllByNameContaining(@Param("filter") String filter, Pageable pageable);
    @Query("SELECT o FROM Organization o WHERE o.active=true")
    List<Organization> findAll();
    @Query("UPDATE Organization o SET o.active=false WHERE o.id=:id")
    @Modifying
    @Transactional
    void delete(@Param("id") Integer id);
}
