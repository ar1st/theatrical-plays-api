package aris.thesis.theatricalplaysapi.entities

import javax.persistence.*

@Entity
@Table(name = "images")
class Image(

    @Column(name = "imageURL", nullable = false)
    var imageURL: String? = null,

    @Column(name = "personID")
    var personId: Int? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null,

    )