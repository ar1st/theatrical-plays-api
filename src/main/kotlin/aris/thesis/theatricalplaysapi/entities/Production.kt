package aris.thesis.theatricalplaysapi.entities

import aris.thesis.theatricalplaysapi.constants.SecurityConstants
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "production")
class Production(
    @Column(name = "Title", nullable = false)
    var title: String? = null,

    @Column(name = "Description", nullable = false, columnDefinition = "LONGTEXT")
    var description: String? = null,

    @Column(name = "URL", nullable = false)
    var url: String? = null,

    @Column(name = "Producer", nullable = false)
    var producer: String? = null,

    @Column(name = "MediaURL", nullable = false)
    var mediaURL: String? = null,

    @Column(name = "Duration", nullable = false)
    var duration: String? = null,

    @Column(name = "OrganizerID")
    var organizerID: Int? = null,

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null,

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null
) {
    constructor(
        title: String,
        description: String,
        url: String,
        producer: String,
        mediaURL: String,
        duration: String,
        organizerId: Int
    ) : this(
        title, description, url, producer, mediaURL, duration, organizerId,
        SecurityConstants.SYSTEM_ID, Date()
    )

}


