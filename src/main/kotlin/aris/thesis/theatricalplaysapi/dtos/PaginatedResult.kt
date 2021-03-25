package aris.thesis.theatricalplaysapi.dtos

class PaginatedResult<T> ( val elements: List<T>, val totalElements: Long, val totalPages: Long)