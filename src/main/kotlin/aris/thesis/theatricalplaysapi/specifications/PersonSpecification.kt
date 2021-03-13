package aris.thesis.theatricalplaysapi.specifications

import aris.thesis.theatricalplaysapi.entities.Person
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


class PersonSpecification(private val criteria: SearchCriteria = SearchCriteria()): Specification<Person> {

    // TODO: only keep : sign, and maybe add not equalls
    override fun toPredicate(root: Root<Person>, p1: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate? {
        when {
            criteria.operation.equals(">", ignoreCase = true) -> {
//                return  builder.greaterThan(root.get(criteria.key), criteria.value as Date)
                return builder.greaterThanOrEqualTo(
                    root.get(criteria.key), criteria.value.toString()
                )
            }
            criteria.operation.equals("<", ignoreCase = true) -> {
                return builder.lessThanOrEqualTo(
                    root.get(criteria.key), criteria.value.toString()
                )
            }
            criteria.operation.equals(":", ignoreCase = true) -> {
                return if (root.get<Any>(criteria.key).javaType == String::class.java) {
                    builder.like(
                        root.get(criteria.key), "%" + criteria.value + "%"
                    )
                } else {
                    builder.equal(root.get<Any>(criteria.key), criteria.value)
                }
            }
            else -> return null
        }
    }

}