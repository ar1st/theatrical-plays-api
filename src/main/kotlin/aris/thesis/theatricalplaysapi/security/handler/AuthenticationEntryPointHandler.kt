package aris.thesis.theatricalplaysapi.security.handler;

import aris.thesis.theatricalplaysapi.rest.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        OutputStream outputStream = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, new ApiResponse<>(null, null, HttpStatus.UNAUTHORIZED.name()));
        outputStream.flush();
    }
}
