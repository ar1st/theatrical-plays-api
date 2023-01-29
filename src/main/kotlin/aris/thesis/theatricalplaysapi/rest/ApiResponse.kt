package aris.thesis.theatricalplaysapi.rest

class ApiResponse<D>(var data: D? = null, var error: ApiError? = null, var status: String)