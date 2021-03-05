package aris.thesis.theatricalplaysapi.dtos

import com.fasterxml.jackson.annotation.JsonAlias

data class ProductionAndRoleResponse(
    @JsonAlias("ID")
    var id: Int,
    @JsonAlias("Title")
    var title: String,
    @JsonAlias("Description")
    var description: String,
    @JsonAlias("MediaURL")
    var mediaURL: String
)
