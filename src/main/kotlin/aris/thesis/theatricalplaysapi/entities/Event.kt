package aris.thesis.theatricalplaysapi.entities

import aris.thesis.theatricalplaysapi.constants.SecurityConstants
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "events")
class Event(
    @Column(name = "ProductionID", nullable = false)
    val productionID: Int? = null,

    @Column(name = "VenueID", nullable = false)
    var venueID: Int? = null,

    @Column(name = "DateEvent", nullable = false)
    var dateEvent: Date? = null,

    @Column(name = "PriceRange", nullable = false)
    var priceRange: String? = null,

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null,

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null
) {
    constructor(productionId: Int, venueId: Int, dateEvent: Date, priceRange: String) : this(
        productionId,
        venueId,
        dateEvent,
        priceRange,
        SecurityConstants.SYSTEM_ID,
        Date()
    )
}