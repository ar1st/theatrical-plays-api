package aris.thesis.theatricalplaysapi.entities

import aris.thesis.theatricalplaysapi.constants.SecurityConstants
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "organizer")
class Organizer(

    @Column(name = "Name", nullable = false)
    var name: String? = null,

    @Column(name = "Address", nullable = false)
    var address: String? = null,

    @Column(name = "Town", nullable = false)
    var town: String? = null,

    @Column(name = "postcode", nullable = false)
    var postcode: String? = null,

    @Column(name = "Phone", nullable = false)
    var phone: String? = null,

    @Column(name = "Email", nullable = false)
    var email: String? = null,

    @Column(name = "Doy", nullable = false)
    var doy: String? = null,

    @Column(name = "Afm", nullable = false)
    var afm: String? = null,

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
        name: String,
        address: String,
        town: String,
        postcode: String,
        phone: String,
        email: String,
        doy: String,
        afm: String
    ) : this(name, address, town, postcode, phone, email, doy, afm, SecurityConstants.SYSTEM_ID, Date())

}