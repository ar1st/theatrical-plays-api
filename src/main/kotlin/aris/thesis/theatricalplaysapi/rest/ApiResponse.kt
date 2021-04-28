package aris.thesis.theatricalplaysapi.rest

class ApiResponse<D,E>(var data: D?, var errors: E?,var status: String)