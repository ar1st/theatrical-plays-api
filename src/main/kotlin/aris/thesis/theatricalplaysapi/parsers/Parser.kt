package aris.thesis.theatricalplaysapi.parsers

interface Parser<T> {
    fun parse(query: String): T
}