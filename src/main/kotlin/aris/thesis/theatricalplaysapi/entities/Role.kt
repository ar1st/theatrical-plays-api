package aris.thesis.theatricalplaysapi.entities

import aris.thesis.theatricalplaysapi.constants.SecurityConstants
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
    @Column(name = "Role", nullable = false)
    var role: String? = null,

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null,

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null
) {
    constructor(role: String?) : this(role, SecurityConstants.SYSTEM_ID, Date())
}