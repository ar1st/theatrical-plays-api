package aris.thesis.theatricalplaysapi.specifications

import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.exceptions.error.wrongQuery
import aris.thesis.theatricalplaysapi.specifications.base.SearchCriteria
import aris.thesis.theatricalplaysapi.specifications.base.SearchOperation
import org.springframework.data.jpa.domain.Specification
import java.lang.IllegalArgumentException
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class ProductionSpecification(private val criteria: SearchCriteria): Specification<Production> {

    override fun toPredicate(root: Root<Production?>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate? {
        return try {
            when (criteria.operation) {
                SearchOperation.EQUALITY -> builder.equal(root.get<Any>(criteria.key), criteria.value)
                SearchOperation.NEGATION -> builder.notEqual(root.get<Any>(criteria.key), criteria.value)
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