package aris.thesis.theatricalplaysapi.rest

import aris.thesis.theatricalplaysapi.exceptions.error.ErrorCode

class ApiError(val message: String, val errorCode: ErrorCode)