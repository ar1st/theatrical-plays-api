package aris.thesis.theatricalplaysapi.rest

class ApiResponse<D>(var data: D?, var error: ApiError?, var status: String)