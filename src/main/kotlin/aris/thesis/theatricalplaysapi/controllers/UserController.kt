package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.services.CustomUserDetailsServiceImpl
import aris.thesis.theatricalplaysapi.dtos.UserDTO
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
class UserController {

    @Autowired
    lateinit var userDetailsService: CustomUserDetailsServiceImpl

    @PostMapping("register")
    fun register(@RequestBody user: UserDTO): ApiResponse<String, String> {
        userDetailsService.save(user)
        return ApiResponse(null, null, HttpStatus.OK.name)
    }

    @PostMapping(value = ["/validate"])
    fun validate(@RequestParam("token") token: String?): ApiResponse<Boolean, String> {
        val tokenValid = userDetailsService.isTokenValid(token)
        return ApiResponse(tokenValid, null, HttpStatus.OK.name)
    }
}