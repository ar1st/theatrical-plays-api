package aris.thesis.theatricalplaysapi.constants

object SecurityConstants {
    const val SIGN_UP_URL = "/api/users/register"
    const val SIGN_IN_URL = "/api/users/login"
    const val VALIDATE_USER_URL = "/api/users/validate"
    const val SECRET = "the!gdAx@"
    const val EXPIRATION_TIME: Long = 432000000
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_TOKEN = "Authorization"
    const val HEADER_USERNAME = "Username"
    const val SYSTEM_ID = 10
}