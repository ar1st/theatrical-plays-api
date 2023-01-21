package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.constants.SecurityConstants.SECRET
import aris.thesis.theatricalplaysapi.dtos.UserDTO
import aris.thesis.theatricalplaysapi.entities.User
import aris.thesis.theatricalplaysapi.exceptions.RestEntityNotFoundException
import aris.thesis.theatricalplaysapi.repositories.AuthorityRepository
import aris.thesis.theatricalplaysapi.repositories.UserRepository
import io.jsonwebtoken.*
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import javax.transaction.Transactional

@Service
class CustomUserDetailsServiceImpl(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    @Autowired
    lateinit var authorityRepository: AuthorityRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email) ?: throw RestEntityNotFoundException()
        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password,
            user.enabled!!,
            true,
            true,
            true,
            getAuthorities(user)
        )
    }

    fun isTokenValid(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(SECRET.toByteArray()).parseClaimsJws(token)
            true
        } catch (ex: SignatureException) {
//            throw ApiExceptionFactory.getApiException(ApiExceptionType.LOGIN_FAILURE, "invalid credentials");
            throw RuntimeException()
        } catch (ex: MalformedJwtException) {
            throw RuntimeException()
        } catch (ex: UnsupportedJwtException) {
            throw RuntimeException()
        } catch (ex: IllegalArgumentException) {
            throw RuntimeException()
        } catch (ex: ExpiredJwtException) {
            throw RuntimeException()
            //            throw ApiExceptionFactory.getApiException(ApiExceptionType.LOGIN_FAILURE, "token expired");
        }
    }

    @Transactional
    fun save(user: UserDTO) {
        if (StringUtils.isEmpty(user.email)) {
            throw RuntimeException()
        }
        if (StringUtils.isEmpty(user.password)) {
            throw RuntimeException()
        }
        if (CollectionUtils.isEmpty(user.authorities)) {
            throw RuntimeException()
        }
        val authorities = authorityRepository.findAuthoritiesByNameIn(user.authorities)
        if (user.authorities.size != authorities.size) {
            throw RuntimeException()
        }
        val registeredUser = User()
        registeredUser.email = user.email
        registeredUser.password = passwordEncoder.encode(user.password)
        registeredUser.enabled = true
        registeredUser.authorities = authorities
        userRepository.save(registeredUser)
    }

    private fun getAuthorities(user: User): Set<GrantedAuthority> {
        val authorities: MutableSet<GrantedAuthority> = HashSet()
        for (authority in user.authorities) {
            val grantedAuthority: GrantedAuthority = SimpleGrantedAuthority(authority.name)
            authorities.add(grantedAuthority)
        }
        return authorities
    }
}