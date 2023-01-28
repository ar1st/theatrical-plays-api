package aris.thesis.theatricalplaysapi.config

import aris.thesis.theatricalplaysapi.constants.SecurityConstants.HEADER_TOKEN
import aris.thesis.theatricalplaysapi.constants.SecurityConstants.HEADER_USERNAME
import aris.thesis.theatricalplaysapi.constants.SecurityConstants.SIGN_UP_URL
import aris.thesis.theatricalplaysapi.constants.SecurityConstants.VALIDATE_USER_URL
import aris.thesis.theatricalplaysapi.repositories.UserRepository
import aris.thesis.theatricalplaysapi.security.filter.JWTAuthenticationFilter
import aris.thesis.theatricalplaysapi.security.filter.JWTAuthorizationFilter
import aris.thesis.theatricalplaysapi.security.handler.AuthenticationEntryPointHandler
import aris.thesis.theatricalplaysapi.services.CustomUserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(Exception::class)
    public override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
            .antMatchers(HttpMethod.POST, VALIDATE_USER_URL).permitAll()
            .antMatchers(
                "/h2-console/**",
                "/swagger-ui/*",
                "/swagger-ui.html",
                "/webjars/**",
                "/v2/**",
                "/swagger-resources/**"
            ).permitAll()
            .anyRequest().permitAll()
            .and()
            .exceptionHandling().authenticationEntryPoint(AuthenticationEntryPointHandler())
            .and()
            .addFilter(JWTAuthenticationFilter(authenticationManager(), userRepository))
            .addFilter(JWTAuthorizationFilter(authenticationManager()))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.headers().frameOptions().disable()
    }

    override fun userDetailsServiceBean(): UserDetailsService {
        return CustomUserDetailsServiceImpl(userRepository, passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedOrigins = listOf("*")
        corsConfiguration.maxAge = 3600L
        corsConfiguration.exposedHeaders = listOf(HEADER_TOKEN, HEADER_USERNAME) // otherwise can not get from client
        corsConfiguration.allowedMethods = listOf("GET", "POST", "DELETE", "PUT")
        corsConfiguration.applyPermitDefaultValues()
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }
}