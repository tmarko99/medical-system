package it.engineering.specification;

import it.engineering.dto.FilterDto;
import it.engineering.entity.Examination;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class ExaminationSpecification {

    public Specification<Examination> getExaminations(FilterDto filterDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(filterDto.getOrganization() != null){
                predicates.add(criteriaBuilder.equal(root.get("organization"), filterDto.getOrganization()));
            }

            if(filterDto.getPatient() != null){
                predicates.add(criteriaBuilder.equal(root.get("patient"), filterDto.getPatient()));
            }

            if(filterDto.getServiceType() != null){
                predicates.add(criteriaBuilder.equal(root.get("serviceType"), filterDto.getServiceType()));
            }

            if(filterDto.getStatus() != null){
                predicates.add(criteriaBuilder.equal(root.get("status"), filterDto.getStatus()));
            }
            if(filterDto.getPriority() != null){
                predicates.add(criteriaBuilder.equal(root.get("priority"), filterDto.getPriority()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
