package aris.thesis.theatricalplaysapi.specifications;

import aris.thesis.theatricalplaysapi.entities.Person;
import aris.thesis.theatricalplaysapi.specifications.base.SearchCriteria;
import aris.thesis.theatricalplaysapi.specifications.base.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PersonSpecificationBuilder {

    private final List<SearchCriteria> params = new ArrayList<>();

    public PersonSpecificationBuilder with(
            String key, String operation, Object value) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            params.add(new SearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<Person> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<Person> result = new PersonSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result =  Specification.where(result).and(new PersonSpecification(params.get(i)));
        }

        return result;
    }
}