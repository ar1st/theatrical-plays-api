package aris.thesis.theratricalplaysapi.entities

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
class Roles : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var iD: Int? = null

    @Column(name = "Role", nullable = false)
    var role: String? = null

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null
    override fun toString(): String {
        return "Roles{" +
                "ID=" + iD + '\'' +
                "role=" + role + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}'
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}