package aris.thesis.theratricalplaysapi.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null

    @Column(name = "Role", nullable = false)
    var role: String? = null

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null
    override fun toString(): String {
        return "Roles{" +
                "ID=" + id + '\'' +
                "role=" + role + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}'
    }
}