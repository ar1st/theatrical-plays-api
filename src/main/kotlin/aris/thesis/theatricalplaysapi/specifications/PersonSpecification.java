package aris.thesis.theatricalplaysapi.specifications;

import aris.thesis.theatricalplaysapi.entities.Person;
import aris.thesis.theatricalplaysapi.specifications.base.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class PersonSpecification implements Specification<Person> {
    private SearchCriteria criteria;

    public PersonSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(
            Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.<String> get(
                        criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.<String> get(
                        criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.<String> get(
                        criteria.getKey()),"%" +  criteria.getValue().toString()  + "%");
            default:
                return null;
        }
    }


}
