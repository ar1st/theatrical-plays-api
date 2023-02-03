package aris.thesis.theatricalplaysapi.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static aris.thesis.theatricalplaysapi.constants.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        if (authenticationToken == null) {
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_TOKEN);
        if (token != null && token.startsWith(TOKEN_PREFIX)) {

            byte[] signingKey = SECRET.getBytes();

            Jws<Claims> parsedToken = Jwts.parser()
                                          .setSigningKey(signingKey)
                                          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""));

            String username = (String) parsedToken.getBody().get("email");

            List<SimpleGrantedAuthority> authorities =
                    ((List<?>) parsedToken.getBody().get("role"))
                            .stream()
                            .map(authority -> new SimpleGrantedAuthority((String) authority))
                            .collect(Collectors.toList());

            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null, authorities);
            }
        }
        return null;
    }
}
