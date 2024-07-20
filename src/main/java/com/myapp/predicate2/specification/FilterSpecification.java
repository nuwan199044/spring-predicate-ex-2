package com.myapp.predicate2.specification;

import com.myapp.predicate2.dto.SearchFilter;
import com.myapp.predicate2.dto.SearchRequest;
import com.myapp.predicate2.enums.GlobalOperator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Specification<T> getSearchSpecification(List<SearchFilter> searchFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SearchFilter sf : searchFilter) {
                predicates.add(criteriaBuilder.equal(root.get(sf.getColumn()), sf.getValue()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<T> getSearchSpecification(SearchRequest searchRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SearchFilter sf : searchRequest.getFilters()) {
                predicates.add(criteriaBuilder.equal(root.get(sf.getColumn()), sf.getValue()));
            }

            if (searchRequest.getGlobalOperator().equals(GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

    public Specification<T> getSearchSpecificationWithEqAndLike(SearchRequest searchRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SearchFilter sf : searchRequest.getFilters()) {
                switch (sf.getOperation()) {
                    case EQUAL :
                        predicates.add(criteriaBuilder.equal(root.get(sf.getColumn()), sf.getValue()));
                        break;
                    case LIKE :
                        predicates.add(criteriaBuilder.like(root.get(sf.getColumn()), "%"+sf.getValue()+"%"));
                        break;
                    case IN :
                        String[] splitValues = sf.getValue().split(",");
                        predicates.add(root.get(sf.getColumn()).in(Arrays.asList(splitValues)));
                        break;
                    case GREATER_THAN :
                        predicates.add(criteriaBuilder.greaterThan(root.get(sf.getColumn()), sf.getValue()));
                        break;
                    case LESS_THAN :
                        predicates.add(criteriaBuilder.lessThan(root.get(sf.getColumn()), sf.getValue()));
                        break;
                    case BETWEEN :
                        String[] splitBetween = sf.getValue().split(",");
                        predicates.add(criteriaBuilder.between(root.get(sf.getColumn()), splitBetween[0], splitBetween[1]));
                        break;
                }
            }

            if (searchRequest.getGlobalOperator().equals(GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

}
