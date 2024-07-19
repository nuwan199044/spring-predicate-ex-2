package com.myapp.predicate2.specification;

import com.myapp.predicate2.dto.SearchFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class FilterSpecification<T> {

    public Specification<T> getSearchSpecification(SearchFilter searchFilter) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchFilter.getColumn()), searchFilter.getValue());
            }
        };
    }

}
