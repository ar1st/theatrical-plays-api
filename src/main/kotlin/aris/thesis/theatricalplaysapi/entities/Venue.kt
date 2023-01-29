package aris.thesis.theatricalplaysapi.entities

import aris.thesis.theatricalplaysapi.constants.SecurityConstants
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "venue")
class Venue(
    @Column(name = "Title", nullable = false)
    var title: String? = null,

    @Column(name = "Address", nullable = false)
    var address: String? = null,

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null,

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null
) {
    constructor(title: String, address: String) : this(title, address, SecurityConstants.SYSTEM_ID, Date())
}