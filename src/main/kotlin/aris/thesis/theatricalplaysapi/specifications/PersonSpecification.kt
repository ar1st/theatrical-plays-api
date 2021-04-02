package aris.thesis.theatricalplaysapi.specifications

import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.exceptions.error.never
import aris.thesis.theatricalplaysapi.exceptions.error.wrongQuery
import aris.thesis.theatricalplaysapi.specifications.base.SearchCriteria
import aris.thesis.theatricalplaysapi.specifications.base.SearchOperation
import org.springframework.data.jpa.domain.Specification
import java.lang.IllegalArgumentException
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class PersonSpecification(private val criteria: SearchCriteria) : Specification<Person> {

    override fun toPredicate(root: Root<Person?>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate? {

        if (root.get<String>(criteria.key).equals("role") ) {

        }

        //todo remove unused operations and throw IllegalArgumentException
        return try {
            when (criteria.operation) {
                SearchOperation.EQUALITY -> builder.equal(root.get<Any>(criteria.key), criteria.value)
                SearchOperation.NEGATION -> builder.notEqual(root.get<Any>(criteria.key), criteria.value)
                SearchOperation.GREATER_THAN -> builder.greaterThan(
                    root.get(
                        criteria.key
                    ), criteria.value.toString()
                )
                SearchOperation.LESS_THAN -> builder.lessThan(
                    root.get(
                        criteria.key
                    ), criteria.value.toString()
                )
                SearchOperation.LIKE -> builder.like(
                    root.get(
                        criteria.key
                    ), "%" + criteria.value.toString() + "%"
                )
                else -> {
                    wrongQuery()
                }
            }
        } catch ( e: IllegalArgumentException) {
            throw e
        }
    }
}