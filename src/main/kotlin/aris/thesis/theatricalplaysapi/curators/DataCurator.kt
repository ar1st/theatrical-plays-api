package aris.thesis.theatricalplaysapi.curators

import aris.thesis.theatricalplaysapi.exceptions.error.invalidFullName

object DataCurator {

    private val fullNameRegex = "^[A-Z\\u0370-\\u03ff\\u1f00-\\u1fff]([- a-zA-Z]|[- \\u0370-\\u03ff\\u1f00-\\u1fff])+$".toRegex(setOf(RegexOption.IGNORE_CASE))

    fun curateFullName(fullName: String) {
        val isValidName = fullNameRegex.containsMatchIn(fullName)

        if (!isValidName) {
            invalidFullName(fullName)
        }
    }

    fun curateRole(fullName: String) {
        val isValidName = fullNameRegex.containsMatchIn(fullName)

        if (!isValidName) {
            invalidFullName(fullName)
        }
    }

    fun curateVenueTitle(title: String) {
        val isValidName = fullNameRegex.containsMatchIn(title)

        if (!isValidName) {
            invalidFullName(title)
        }
    }
}