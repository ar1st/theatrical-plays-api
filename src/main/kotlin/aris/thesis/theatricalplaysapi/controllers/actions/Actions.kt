package aris.thesis.theatricalplaysapi.controllers.actions

object Actions {
    object Production: NamedActionBridge("ProductionActions")
    object Venue: NamedActionBridge("VenueActions")

    object Person: NamedActionBridge("PersonActions")
    object Organizer: NamedActionBridge("OrganizerActions")
}