package aris.thesis.theratricalplaysapi.entities

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "venue")
class Venue : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var iD: Int? = null

    @Column(name = "Title", nullable = false)
    var title: String? = null

    @Column(name = "Address", nullable = false)
    var address: String? = null

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null
    override fun toString(): String {
        return "Venue{" +
                "ID=" + iD + '\'' +
                "title=" + title + '\'' +
                "address=" + address + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}'
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}