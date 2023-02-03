package aris.thesis.theatricalplaysapi.controllers.base

import aris.thesis.theatricalplaysapi.exceptions.*
import aris.thesis.theatricalplaysapi.exceptions.error.ErrorCode
import aris.thesis.theatricalplaysapi.rest.ApiError
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@ResponseBody
class RestControllerExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(InvalidFullNameException::class)
    fun handleFullNameException(ex: InvalidFullNameException): ApiResponse<String> {
        return ApiResponse(
            null,
            ApiError("Invalid format name: ${ex.fullName}", ErrorCode.E40001),
            HttpStatus.BAD_REQUEST.name
        )
    }

    @ExceptionHandler(RestEntityNotFoundException::class)
    fun handleRestEntityNotFoundException(ex: RestEntityNotFoundException): ApiResponse<String> {
        return ApiResponse(
            null,
            ApiError("Entity ${ex.type} with id ${ex.id} not found", ErrorCode.E40401),
            HttpStatus.BAD_REQUEST.name
        )
    }

    @ExceptionHandler(IllegalDeleteException::class)
    fun handleIllegalDeleteException(ex: IllegalDeleteException): ApiResponse<String> {
        return ApiResponse(
            null,
            ApiError("Tried to delete referenced data", ErrorCode.E40002),
            HttpStatus.BAD_REQUEST.name
        )
    }

    @ExceptionHandler(QueryParsingException::class)
    fun handleQueryParsingException(ex: QueryParsingException): ApiResponse<String> {
        return ApiResponse(
            null,
            ApiError("Invalid query", ErrorCode.E40003),
            HttpStatus.BAD_REQUEST.name
        )
    }

    @ExceptionHandler(MissingRequestParameterException::class)
    fun handleMissingRequestParameterException(ex: MissingRequestParameterException): ApiResponse<String> {
        return ApiResponse(
            null,
            ApiError("Parameter ${ex.parameter} is missing from request", ErrorCode.E40004),
            HttpStatus.BAD_REQUEST.name
        )
    }


}


