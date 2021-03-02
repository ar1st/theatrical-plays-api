package aris.thesis.theratricalplaysapi.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "organizer")
class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null

    @Column(name = "Name", nullable = false)
    var name: String? = null

    @Column(name = "Address", nullable = false)
    var address: String? = null

    @Column(name = "Town", nullable = false)
    var town: String? = null

    @Column(name = "postcode", nullable = false)
    var postcode: String? = null

    @Column(name = "Phone", nullable = false)
    var phone: String? = null

    @Column(name = "Email", nullable = false)
    var email: String? = null

    @Column(name = "Doy", nullable = false)
    var doy: String? = null

    @Column(name = "Afm", nullable = false)
    var afm: String? = null

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null
    override fun toString(): String {
        return "Organizer{" +
                "ID=" + id + '\'' +
                "name=" + name + '\'' +
                "address=" + address + '\'' +
                "town=" + town + '\'' +
                "postcode=" + postcode + '\'' +
                "phone=" + phone + '\'' +
                "email=" + email + '\'' +
                "doy=" + doy + '\'' +
                "afm=" + afm + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}'
    }
}