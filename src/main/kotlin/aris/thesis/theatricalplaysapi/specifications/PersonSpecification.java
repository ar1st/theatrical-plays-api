package aris.thesis.theatricalplaysapi.specifications;

import aris.thesis.theatricalplaysapi.entities.Person;
import aris.thesis.theatricalplaysapi.specifications.base.SearchCriteria;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static aris.thesis.theatricalplaysapi.exceptions.error.ErrorsKt.wrongQuery;


public class PersonSpecification implements Specification<Person> {
    private final SearchCriteria criteria;

    public PersonSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(
            @NotNull Root<Person> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        //todo remove unused operations
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(
                        criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(
                        criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(
                        criteria.getKey()),"%" +  criteria.getValue().toString()  + "%");
            default:
                wrongQuery();
                return null;
        }
    }


}
