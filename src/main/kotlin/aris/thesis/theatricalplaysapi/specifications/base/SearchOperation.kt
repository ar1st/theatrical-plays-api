package aris.thesis.theatricalplaysapi.specifications.base

import aris.thesis.theatricalplaysapi.exceptions.error.wrongQuery

enum class SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE, CONTAINS;

    companion object {
        val SIMPLE_OPERATION_SET = arrayOf(":", "!", ">", "<", "~")
        fun getSimpleOperation(input: Char): SearchOperation {
            return when (input) {
                ':' -> EQUALITY
                '!' -> NEGATION
                '>' -> GREATER_THAN
                '<' -> LESS_THAN
                '~' -> LIKE
                else -> {
                    wrongQuery()
                }
            }
        }
    }
}