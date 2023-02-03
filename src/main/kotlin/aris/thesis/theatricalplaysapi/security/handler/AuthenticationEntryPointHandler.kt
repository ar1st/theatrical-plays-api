package aris.thesis.theatricalplaysapi.security.handler

import aris.thesis.theatricalplaysapi.rest.ApiResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.IOException
import java.io.OutputStream
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationEntryPointHandler : AuthenticationEntryPoint {

    @Throws(IOException::class, ServletException::class)
    override fun commence(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse,
                          e: AuthenticationException) {

        httpServletResponse.status = HttpStatus.UNAUTHORIZED.value()
        val outputStream: OutputStream = httpServletResponse.outputStream

        val mapper = ObjectMapper()
        mapper.writeValue(outputStream, ApiResponse<Any>(null, null, HttpStatus.UNAUTHORIZED.name))
        outputStream.flush()
    }
}