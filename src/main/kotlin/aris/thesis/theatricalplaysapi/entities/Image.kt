package aris.thesis.theatricalplaysapi.entities

import javax.persistence.*

@Entity
@Table(name = "images")
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null

    @Column(name = "imgURL", nullable = false)
    var imgURL: String? = null
}