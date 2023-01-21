package aris.thesis.theatricalplaysapi.security.filter;

import aris.thesis.theatricalplaysapi.entities.User;
import aris.thesis.theatricalplaysapi.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static aris.thesis.theatricalplaysapi.constants.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        setFilterProcessesUrl(SIGN_IN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(email == null) {
//            throw ApiExceptionFactory.getApiException(ApiExceptionType.BAD_REQUEST, "username");
            throw new RuntimeException();
        }

        if(password == null) {
            throw new RuntimeException();
//            throw ApiExceptionFactory.getApiException(ApiExceptionType.BAD_REQUEST, "password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {

        org.springframework.security.core.userdetails.User user = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal());

        User currentUser = userRepository.findByEmail(user.getUsername());

        if(currentUser == null) {
//            throw ApiExceptionFactory.getApiException(ApiExceptionType.NOT_FOUND, "user");
            throw new RuntimeException();
        }

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("role", roles)
                .claim("email", currentUser.getEmail())
                .claim("id", currentUser.getId())
                .compact();

        response.addHeader(HEADER_TOKEN, TOKEN_PREFIX + token);
        //response.addHeader(HEADER_USERNAME, user.getUsername());
    }

}
