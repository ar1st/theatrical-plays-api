package aris.thesis.theatricalplaysapi.specifications.builder

import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.specifications.PersonSpecification
import aris.thesis.theatricalplaysapi.specifications.base.SearchCriteria
import aris.thesis.theatricalplaysapi.specifications.base.SearchOperation
import org.springframework.data.jpa.domain.Specification

class PersonSpecificationBuilder {
    private val params = mutableListOf<SearchCriteria>()

    fun with(key: String, operation: String, value: Any): PersonSpecificationBuilder {
        val op = SearchOperation.getSimpleOperation(operation[0])
        params.add(SearchCriteria(key, op, value))
        return this
    }

    fun build(): Specification<Person>? {
        if (params.size == 0) {
            return null
        }
        var result: Specification<Person> = PersonSpecification(params[0])

        for (i in 1 until params.size) {
            result = Specification.where(result).and(
                PersonSpecification(
                    params[i]
                )
            )
        }
        return result
    }
}