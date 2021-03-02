package aris.thesis.theatricalplaysapi.entities

import javax.persistence.*

@Entity
@Table(name = "system")
class System {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null

    @Column(name = "name", nullable = false)
    var name: String? = null
    override fun toString(): String {
        return "System{" +
                "ID=" + id + '\'' +
                "name=" + name + '\'' +
                '}'
    }
}