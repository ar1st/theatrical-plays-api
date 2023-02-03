package aris.thesis.theatricalplaysapi.entities

import aris.thesis.theatricalplaysapi.constants.SecurityConstants
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "contributions")
class Contribution(
    @Column(name = "ProductionID", nullable = false)
    var productionID: Int? = null,

    @Column(name = "PeopleID", nullable = false)
    var peopleID: Int? = null,

    @Column(name = "RoleID", nullable = false)
    var roleID: Int? = null,

    @Column(name = "subRole")
    var subRole: String? = null,

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null,

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null
) {
    constructor(productionId: Int, peopleId: Int, roleId: Int, subRole: String) : this(
        productionId,
        peopleId,
        roleId,
        subRole,
        SecurityConstants.SYSTEM_ID,
        Date()
    )
}