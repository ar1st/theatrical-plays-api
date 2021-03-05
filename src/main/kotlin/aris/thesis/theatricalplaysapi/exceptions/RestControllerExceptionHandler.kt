package aris.thesis.theatricalplaysapi.exceptions

import aris.thesis.theatricalplaysapi.controllers.base.RestActionController
import aris.thesis.theatricalplaysapi.exceptions.response.RestResponse
import org.hibernate.exception.ConstraintViolationException
import org.hibernate.exception.DataException
import org.hibernate.exception.GenericJDBCException
import org.hibernate.exception.SQLGrammarException
import org.springframework.beans.ConversionNotSupportedException
import org.springframework.beans.TypeMismatchException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.validation.BindException
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.async.AsyncRequestTimeoutException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.support.MissingServletRequestPartException
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.util.WebUtils


@ControllerAdvice(assignableTypes = [RestActionController::class])
class RestControllerExceptionHandler : ResponseEntityExceptionHandler() {

    @Autowired
    lateinit var responseFactory: RestResponseFactory

    private val cache: MutableMap<String, HttpStatus> = mutableMapOf()

    private fun httpStatusFromString(string: String): HttpStatus {
        if (cache.containsKey(string)) {
            return cache[string] ?: HttpStatus.INTERNAL_SERVER_ERROR
        } else {
            var status = HttpStatus.INTERNAL_SERVER_ERROR

            for (httpStatus in HttpStatus.values()) {
                if (httpStatus.reasonPhrase.equals(string, ignoreCase = true)
                    || httpStatus.value().toString() == string
                    || httpStatus.name.equals(string, ignoreCase = true)
                ) {
                    status = httpStatus
                    break
                }
            }

            cache[string] = status
            return status
        }
    }

    private fun createErrorResponse(status: HttpStatus, description: String): RestResponse {
        val HttpStatusTypeGeneric = 0
        val HttpStatusTypeInformational = 1
        val HttpStatusTypeSuccessful = 2
        val HttpStatusTypeRedirection = 3
        val HttpStatusTypeClientError = 4
        val HttpStatusTypeServerError = 5

        var statusType = HttpStatusTypeGeneric

        when {
            status.is1xxInformational -> {
                statusType = HttpStatusTypeInformational
            }
            status.is2xxSuccessful -> {
                statusType = HttpStatusTypeSuccessful
            }
            status.is3xxRedirection -> {
                statusType = HttpStatusTypeRedirection
            }
            status.is4xxClientError -> {
                statusType = HttpStatusTypeClientError
            }
            status.is5xxServerError -> {
                statusType = HttpStatusTypeServerError
            }
        }


        val cause = when (statusType) {
            HttpStatusTypeInformational -> "Unknown. Http Status implied informational status, but an error occurred."
            HttpStatusTypeSuccessful -> "Unknown. Http Status implied successful status, but an error occurred."
            HttpStatusTypeRedirection -> "Unknown. Http Status implied redirection status, but an error occurred."
            HttpStatusTypeClientError -> "Unknown. Generic Client Error."
            HttpStatusTypeServerError -> "Unknown. Generic Server Error."
            else -> "Generic unknown."
        }

        val error = responseFactory.createError(type = "ServerError", description = description, cause = cause)
        val response = responseFactory.createResponse()

        response.withError(error)
        response.withStatus(status.reasonPhrase)

        return response
    }


    ////// ............... Spring Exceptions / General handling

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        if (HttpStatus.INTERNAL_SERVER_ERROR == status) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST)
        }

        val responseBody = if (body is RestResponse) {
            body
        } else {
            createErrorResponse(
                status, "Exception: ${ex.javaClass.name}." +
                        "\nMessage:${ex.message}."
            )
        }

        return ResponseEntity(responseBody, headers, status)
    }

    /*
     * BindException: This exception is thrown when fatal binding errors occur.
     */
    override fun handleBindException(
        ex: BindException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val objName = ex.objectName
        val fieldBindings = StringBuilder()
        val globalBindings = StringBuilder()

        for (error in ex.bindingResult.fieldErrors) {
            fieldBindings.append("\n")
                .append(error.field).append(" : ").append(error.defaultMessage)
        }

        for (error in ex.bindingResult.globalErrors) {
            globalBindings.append("\n")
                .append(error.objectName).append(" : ").append(error.defaultMessage)
        }

        val status = HttpStatus.BAD_REQUEST
        val error = createErrorResponse(
            status, "Exception: BindException. " +
                    "\nMessage:Error binding object $objName." +
                    "\nInvalid field bindings: $fieldBindings." +
                    "\nGlobal binding errors: $globalBindings."
        )

        return handleExceptionInternal(ex, error, headers, status, request)
    }

    /*
     * MissingPathVariableException
     */
    override fun handleMissingPathVariable(
        ex: MissingPathVariableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val error = createErrorResponse(
            status, "Exception: MissingPathVariableException." +
                    "\nMessage:Path variable ${ex.variableName} is missing."
        )

        return ResponseEntity(error, headers, status)
    }

    /*
     * MissingServletRequestPartException
     */
    override fun handleMissingServletRequestPart(
        ex: MissingServletRequestPartException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val error = createErrorResponse(
            status, "Exception: MissingServletRequestPartException. " +
                    "\nMessage:Request part ${ex.requestPartName} is missing."
        )

        return ResponseEntity(error, headers, status)
    }

    /*
     * MissingServletRequestParameterException
     */
    override fun handleMissingServletRequestParameter(
        ex: MissingServletRequestParameterException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val error = createErrorResponse(
            status, "Exception: MissingServletRequestParameterException. " +
                    "\nMessage:Request parameter ${ex.parameterName} is missing."
        )

        return ResponseEntity(error, headers, status)
    }

    /*
     * ConstrainViolationException: This exception reports the result of constraint violations.
     */
//    @ExceptionHandler(ConstraintViolationException::class)
//    fun handleConstraintViolation(ex: ConstraintViolationException, request: WebRequest): ResponseEntity<Any> {
//        val errors = StringBuilder()
//
////        for (violation in ex.constraintViolations) {
////            errors.append("\n")
////                .append(violation.rootBeanClass.name + " > " + violation.propertyPath + ": " + violation.message)
////        }
//
//        val status = HttpStatus.BAD_REQUEST
//        val error = createErrorResponse(
//            status, "Exception: ConstrainViolationException. " +
//                    "\nMessage:Constrain errors:$errors"
//        )
//
//        return ResponseEntity(error, HttpHeaders(), status)
//    }

    /*
     * TypeMismatchException: This exception is thrown when try to set bean property appendedWithAND wrong type.
     */
    override fun handleTypeMismatch(
        ex: TypeMismatchException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        val error = createErrorResponse(
            status, "Exception: TypeMismatchException. " +
                    "\nMessage:Object type mismatch, expected type ${ex.requiredType?.name}."
        )

        return ResponseEntity(error, headers, status)
    }


    /*
     * MethodArgumentTypeMismatchException: This exception is thrown when method argument is not the expected type.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(
        ex: MethodArgumentTypeMismatchException,
        request: WebRequest
    ): ResponseEntity<Any> {

        val status = HttpStatus.BAD_REQUEST
        val error = createErrorResponse(
            status, "Exception: MethodArgumentTypeMismatchException. " +
                    "\nMessage:Request parameter ${ex.name} is invalid, expected type ${ex.requiredType?.name}."
        )

        return ResponseEntity(error, HttpHeaders(), status)
    }

    /*
     * NoHandlerFoundException: handles NOT FOUND 404 errors
     */
    override fun handleNoHandlerFoundException(
        ex: NoHandlerFoundException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        val status = HttpStatus.NOT_FOUND
        val error = createErrorResponse(
            status, "Exception: NoHandlerFoundException. " +
                    "\nMessage:No handler found for method ${ex.httpMethod} at url ${ex.requestURL}."
        )

        return ResponseEntity(error, headers, status)
    }

    /*
     * HttpRequestMethodNotSupportedException: Invalid HTTP Method
     */
    override fun handleHttpRequestMethodNotSupported(
        ex: HttpRequestMethodNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val builder = StringBuilder()
        ex.supportedHttpMethods?.forEach { t -> builder.append(t.toString() + ", ") }
        builder.replace(builder.length - 3, builder.length - 1, "")

        val status = HttpStatus.METHOD_NOT_ALLOWED
        val error = createErrorResponse(
            status, "Exception: HttpRequestMethodNotSupportedException. " +
                    "\nMessage:HTTP ${ex.method} method is not supported for this request. " +
                    "\nSupported methods are $builder."
        )

        return ResponseEntity(error, headers, status)
    }

    /*
     * HttpMediaTypeNotSupportedException: Media not supported exception
     */
    override fun handleHttpMediaTypeNotSupported(
        ex: HttpMediaTypeNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val builder = StringBuilder()
        ex.supportedMediaTypes.forEach { t -> builder.append(t.toString() + ", ") }
        builder.replace(builder.length - 3, builder.length - 1, "")

        val status = HttpStatus.UNSUPPORTED_MEDIA_TYPE
        val error = createErrorResponse(
            status, "Exception: HttpMediaTypeNotSupportedException. " +
                    "\nMessage:${ex.contentType} media type is not supported. Supported media types are $builder."
        )

        return ResponseEntity(error, headers, status)
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val status = HttpStatus.UNPROCESSABLE_ENTITY
        val error = createErrorResponse(
            status, "Exception: HttpMessageNotReadableException. " +
                    "\nMessage:HTTP message could not be read. " +
                    "\nDetails:${ex.message}."
        )

        return ResponseEntity(error, headers, status)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val error = createErrorResponse(
            status, "Exception: MethodArgumentNotValidException. " +
                    "\nMessage:Method parameter ${ex.parameter.parameterName} of method ${ex.parameter.method} is invalid, expected type ${ex.parameter.parameterType.name}."
        )

        return ResponseEntity(error, headers, status)
    }

    override fun handleHttpMediaTypeNotAcceptable(
        ex: HttpMediaTypeNotAcceptableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val builder = StringBuilder()
        ex.supportedMediaTypes.forEach { t -> builder.append(t.toString() + ", ") }
        builder.replace(builder.length - 3, builder.length - 1, "")

        val status = HttpStatus.UNSUPPORTED_MEDIA_TYPE
        val error = createErrorResponse(
            status, "Exception: HttpMediaTypeNotAcceptableException. " +
                    "\nMessage:Media type is not acceptable. Supported media types are $builder."
        )

        return ResponseEntity(error, HttpHeaders(), status)
    }

    override fun handleHttpMessageNotWritable(
        ex: HttpMessageNotWritableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val error = createErrorResponse(
            status, "Exception: HttpMessageNotWritableException. " +
                    "\nMessage:HTTP message could not be written. " +
                    "\nDetails:${ex.message}."
        )

        return ResponseEntity(error, HttpHeaders(), status)
    }

    override fun handleServletRequestBindingException(
        ex: ServletRequestBindingException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val error = createErrorResponse(
            status, "Exception: ServletRequestBindingException. " +
                    "\nMessage: Server Binding Error." +
                    "\nDetails: ${ex.message}."
        )

        return handleExceptionInternal(ex, error, headers, status, request)
    }

    override fun handleAsyncRequestTimeoutException(
        ex: AsyncRequestTimeoutException,
        headers: HttpHeaders,
        status: HttpStatus,
        webRequest: WebRequest
    ): ResponseEntity<Any>? {
        val error = createErrorResponse(
            status, "Exception: AsyncRequestTimeoutException. " +
                    "\nMessage: Server Async Request Timeout Error." +
                    "\nDetails: ${ex.message}."
        )

        return handleExceptionInternal(ex, error, headers, status, webRequest)
    }

    override fun handleConversionNotSupported(
        ex: ConversionNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val error = createErrorResponse(
            status, "Exception: ConversionNotSupportedException. " +
                    "\nMessage: Conversion of property ${ex.propertyName} appendedWithAND value ${ex.value} to type ${ex.requiredType?.name} is not supported."
        )

        return handleExceptionInternal(ex, error, headers, status, request)
    }

    ////// ............... Generic Exceptions

    /*
     * RestApi Exception Handler
     */
    @ExceptionHandler(RestException::class)
    fun handleRestApiExceptions(ex: RestException, request: WebRequest): ResponseEntity<Any> {
        val errors = ex.toRestErrors(responseFactory)
        val response = responseFactory.createResponse()

        for (error in errors) {
            response.withError(error)
        }

        val status = httpStatusFromString(ex.status())
        response.withStatus(status.name)

        return ResponseEntity(response, HttpHeaders(), status)
    }

    ////// ............... Hibernate - SQL Exceptions

    @ExceptionHandler(SQLGrammarException::class)
    fun handleSQLGrammarException(ex: SQLGrammarException, request: WebRequest): ResponseEntity<Any> {
        //org.hibernate.exception.SQLGrammarException
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val error = createErrorResponse(
            status, "Exception: SQLGrammarException. " +
                    "\nMessage:Internal SQL Server error. " +
                    "\nDetails:${ex.message}."
        )

        return ResponseEntity(error, HttpHeaders(), status)
    }


    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException::class)
    fun handleSQLConstraintViolationException(
        ex: org.hibernate.exception.ConstraintViolationException,
        request: WebRequest
    ): ResponseEntity<Any> {
        //org.hibernate.exception.ConstraintViolationException
        val status = HttpStatus.BAD_REQUEST
        val error = createErrorResponse(
            status, "Exception: ConstraintViolationException. " +
                    "\nMessage:Invalid data supplied. " +
                    "\nDetails:${ex.message}."
        )

        return ResponseEntity(error, HttpHeaders(), status)
    }

    @ExceptionHandler(DataException::class)
    fun handleSQLDataException(ex: DataException, request: WebRequest): ResponseEntity<Any> {
        //org.hibernate.exception.DataException
        val status = HttpStatus.UNPROCESSABLE_ENTITY
        val error = createErrorResponse(
            status, "Exception: DataException. " +
                    "\nMessage:Invalid data types supplied. " +
                    "\nDetails:${ex.message}."
        )

        return ResponseEntity(error, HttpHeaders(), status)
    }


    @ExceptionHandler(GenericJDBCException::class)
    fun handleSQLGenericJDBCException(ex: GenericJDBCException, request: WebRequest): ResponseEntity<Any> {
        //org.hibernate.exception.GenericJDBCException
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val error = createErrorResponse(
            status, "Exception: GenericJDBCException. " +
                    "\nMessage:Generic SQL error. " +
                    "\nDetails:${ex.message}."
        )

        return ResponseEntity(error, HttpHeaders(), status)
    }
}