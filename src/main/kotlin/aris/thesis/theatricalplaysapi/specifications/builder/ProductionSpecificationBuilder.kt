package aris.thesis.theatricalplaysapi.specifications.builder

import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.specifications.ProductionSpecification
import aris.thesis.theatricalplaysapi.specifications.base.SearchCriteria
import aris.thesis.theatricalplaysapi.specifications.base.SearchOperation
import org.springframework.data.jpa.domain.Specification

class ProductionSpecificationBuilder {
    private val params = mutableListOf<SearchCriteria>()

    fun with(key: String, operation: String, value: Any): ProductionSpecificationBuilder {
        val op = SearchOperation.getSimpleOperation(operation[0])
        params.add(SearchCriteria(key, op, value))
        return this
    }

    fun build(): Specification<Production>? {
        if (params.size == 0) {
            return null
        }
        var result: Specification<Production> = ProductionSpecification(params[0])

        for (i in 1 until params.size) {
            result = Specification.where(result).and(
                ProductionSpecification(
                    params[i]
                )
            )
        }
        return result
    }
}