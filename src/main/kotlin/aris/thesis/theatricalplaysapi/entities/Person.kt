package aris.thesis.theatricalplaysapi.entities

import aris.thesis.theatricalplaysapi.constants.SecurityConstants
import java.util.*
import javax.persistence.*

@Table(name = "persons")
@Entity
class Person(
    @Column(name = "Fullname")
    val fullName: String? = null,

    @Column(name = "SystemID")
    val systemID: Int? = null,

    @Column(name = "timestamp")
    val timestamp: Date? = null,

    @OneToMany(mappedBy = "person", cascade = [CascadeType.ALL], orphanRemoval = true)
    val images: List<Image>,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    val id: Int? = null,
) {
    constructor(fullName: String?) : this(fullName, SecurityConstants.SYSTEM_ID, Date(), emptyList())

}