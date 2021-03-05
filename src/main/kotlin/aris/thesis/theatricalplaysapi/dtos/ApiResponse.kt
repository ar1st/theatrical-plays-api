package aris.thesis.theatricalplaysapi.dtos

class ApiResponse<D,E>(var data: D?, var errors: E?,var status: String)