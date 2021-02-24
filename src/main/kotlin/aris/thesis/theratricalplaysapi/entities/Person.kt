package aris.thesis.theratricalplaysapi.entities

import java.util.*
import javax.persistence.*

@Table(name = "persons")
@Entity
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var iD: Int? = null

    @Column(name = "Fullname", nullable = false)
    var fullname: String? = null

    @Column(name = "SystemID", nullable = false)
    var systemID: Int? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null
    override fun toString(): String {
        return "Persons{" +
                "ID=" + iD + '\'' +
                "fullname=" + fullname + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}'
    }
}