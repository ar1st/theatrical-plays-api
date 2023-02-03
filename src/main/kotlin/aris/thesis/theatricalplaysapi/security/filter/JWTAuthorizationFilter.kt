package aris.thesis.theatricalplaysapi.security.filter

import aris.thesis.theatricalplaysapi.constants.SecurityConstants.HEADER_TOKEN
import aris.thesis.theatricalplaysapi.constants.SecurityConstants.SECRET
import aris.thesis.theatricalplaysapi.constants.SecurityConstants.TOKEN_PREFIX
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager?) :
    BasicAuthenticationFilter(authenticationManager) {
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authenticationToken = getAuthentication(request)
        if (authenticationToken == null) {
            chain.doFilter(request, response)
            return
        }
        SecurityContextHolder.getContext().authentication = authenticationToken
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(HEADER_TOKEN)
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            val signingKey: ByteArray = SECRET.toByteArray()
            val parsedToken = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
            val username = parsedToken.body["email"] as String?
            val authorities = (parsedToken.body["role"] as List<*>)
                .stream()
                .map { authority: Any? -> SimpleGrantedAuthority(authority as String?) }
                .collect(Collectors.toList())
            if (username != null) {
                return UsernamePasswordAuthenticationToken(username, null, authorities)
            }
        }
        return null
    }
}