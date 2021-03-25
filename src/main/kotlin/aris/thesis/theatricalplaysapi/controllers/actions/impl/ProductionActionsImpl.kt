package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.def.ProductionActions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import aris.thesis.theatricalplaysapi.utils.paginated
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse

@Component
@Suppress("unused")
class ProductionActionsImpl: ProductionActions, ModelServiceConsumer<ProductionService>() {

    override fun getProduction(productionId: Int, response: HttpServletResponse): ApiResponse<ProductionDTO, String> {
        val production = service.findById(productionId) ?: notFound("Production",productionId.toString())

        return ApiResponse( ProductionDTO(production),null, HttpStatus.OK.name)
    }

    override fun getProductions(page: Int, size: Int): ApiResponse<Page<ProductionDTO>, String> {
        val paginatedResult = if (page >= 0 && size > 0)
            service.findAll(PageRequest.of(page, size))
        else
            service.findAll( Pageable.unpaged())


        return ApiResponse(paginatedResult.map { ProductionDTO(it) }, null, HttpStatus.OK.name)
    }
}