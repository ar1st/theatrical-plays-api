package aris.thesis.theatricalplaysapi.security.filter

import aris.thesis.theatricalplaysapi.constants.SecurityConstants.EXPIRATION_TIME
import aris.thesis.theatricalplaysapi.constants.SecurityConstants.HEADER_TOKEN
import aris.thesis.theatricalplaysapi.constants.SecurityConstants.SECRET
import aris.thesis.theatricalplaysapi.constants.SecurityConstants.SIGN_IN_URL
import aris.thesis.theatricalplaysapi.constants.SecurityConstants.TOKEN_PREFIX
import aris.thesis.theatricalplaysapi.repositories.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(private val authenticationManager: AuthenticationManager,
                              private val userRepository: UserRepository)
    : UsernamePasswordAuthenticationFilter() {
    init {
        setFilterProcessesUrl(SIGN_IN_URL)
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val email = request.getParameter("email")
        val password = request.getParameter("password")

        if (email == null) {
//            throw ApiExceptionFactory.getApiException(ApiExceptionType.BAD_REQUEST, "username");
            throw RuntimeException()
        }

        if (password == null) {
            throw RuntimeException()
            //            throw ApiExceptionFactory.getApiException(ApiExceptionType.BAD_REQUEST, "password");
        }

        val authentication: Authentication = UsernamePasswordAuthenticationToken(email, password)
        return authenticationManager.authenticate(authentication)
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse,
                                          chain: FilterChain, authResult: Authentication) {
        val user = authResult.principal as User
        val currentUser = userRepository.findByEmail(user.username)
            ?: //            throw ApiExceptionFactory.getApiException(ApiExceptionType.NOT_FOUND, "user");
            throw RuntimeException()

        val roles = user.authorities
            .stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.toList())

        val token: String = Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, SECRET.toByteArray())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .claim("role", roles)
            .claim("email", currentUser.email)
            .claim("id", currentUser.id)
            .compact()

        response.addHeader(HEADER_TOKEN, TOKEN_PREFIX + token)
    }
}