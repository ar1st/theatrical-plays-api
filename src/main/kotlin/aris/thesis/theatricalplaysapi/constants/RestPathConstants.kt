package aris.thesis.theatricalplaysapi.constants

object RestPathConstants {
    //people
    const val REST_BASE_PATH_PEOPLE = "/api/people"
    const val REST_PATH_PERSON_ID = "/{personId}"
    const val REST_PATH_PEOPLE = "/people"
    const val REST_PATH_ROLE = "/role"
    const val REST_PATH_LETTER = "/letter"
    const val REST_PATH_PHOTOS = "/photos"

    const val REST_PATH_IMAGES = "/api/images"
    const val REST_PATH_ROLES = "/api/roles"
    const val REST_PATH_ORGANIZERS = "/api/organizers"

    //productions
    const val REST_BASE_PATH_PRODUCTIONS = "/api/productions"
    const val REST_PATH_PRODUCTION_ID = "/{productionId}"
    const val REST_PATH_PRODUCTIONS = "/productions"
    const val REST_PATH_LATEST = "/latest"

    //venues
    const val REST_BASE_PATH_VENUES = "/api/venues"
    const val REST_PATH_VENUE_ID = "/{venueId}"
    const val REST_PATH_VENUES = "/venues"

    //events
    const val REST_PATH_EVENTS = "/events"
    const val REST_PATH_EVENT_ID = "/{eventId}"

    //contributions
    const val REST_PATH_CONTRIBUTIONS = "/contributions"
    const val REST_PATH_CONTRIBUTION_ID = "/{contributionId}"

    const val REST_PATH_SEARCH = "/search"
    const val MEDIA_TYPE_UTF_8 = "; charset=utf-8"
}



