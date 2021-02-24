package aris.thesis.theratricalplaysapi.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "production")
class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var iD: Int? = null

    @Column(name = "OrganizerID")
    var organizerID: Int? = null

    @Column(name = "Title", nullable = false)
    var title: String? = null

    @Column(name = "URL", nullable = false)
    var uRL: String? = null

    @Column(name = "Producer", nullable = false)
    var producer: String? = null

    @Column(name = "MediaURL", nullable = false)
    var mediaURL: String? = null

    @Column(name = "Duration", nullable = false)
    var duration: String? = null

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null

    @Column(name = "Description", nullable = false)
    var description: String? = null
    override fun toString(): String {
        return "Production{" +
                "ID=" + iD + '\'' +
                "organizerID=" + organizerID + '\'' +
                "title=" + title + '\'' +
                "URL=" + uRL + '\'' +
                "producer=" + producer + '\'' +
                "mediaURL=" + mediaURL + '\'' +
                "duration=" + duration + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                "description=" + description + '\'' +
                '}'
    }
}