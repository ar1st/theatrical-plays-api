package aris.thesis.theratricalplaysapi.entities

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "events")
class Event : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var iD: Int? = null

    @Column(name = "ProductionID", nullable = false)
    var productionID: Int? = null

    @Column(name = "VenueID", nullable = false)
    var venueID: Int? = null

    @Column(name = "DateEvent", nullable = false)
    var dateEvent: Date? = null

    @Column(name = "PriceRange", nullable = false)
    var priceRange: String? = null

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null
    override fun toString(): String {
        return "Events{" +
                "ID=" + iD + '\'' +
                "productionID=" + productionID + '\'' +
                "venueID=" + venueID + '\'' +
                "dateEvent=" + dateEvent + '\'' +
                "priceRange=" + priceRange + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}'
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}