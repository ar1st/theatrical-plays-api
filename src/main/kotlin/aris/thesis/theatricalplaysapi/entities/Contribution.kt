package aris.thesis.theatricalplaysapi.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "contributions")
class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null

    @Column(name = "PeopleID", nullable = false)
    var peopleID: Int? = null

    @Column(name = "ProductionID", nullable = false)
    var productionID: Int? = null

    @Column(name = "RoleID", nullable = false)
    var roleID: Int? = null

    @Column(name = "subRole")
    var subRole: String? = null

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null
    override fun toString(): String {
        return "Contributions{" +
                "ID=" + id + '\'' +
                "peopleID=" + peopleID + '\'' +
                "productionID=" + productionID + '\'' +
                "roleID=" + roleID + '\'' +
                "subRole=" + subRole + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                "subRole=" + subRole + '\'' +
                '}'
    }
}