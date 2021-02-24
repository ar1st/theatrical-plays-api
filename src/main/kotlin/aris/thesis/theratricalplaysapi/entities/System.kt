package aris.thesis.theratricalplaysapi.entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "system")
class System : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var iD: Int? = null

    @Column(name = "name", nullable = false)
    var name: String? = null
    override fun toString(): String {
        return "System{" +
                "ID=" + iD + '\'' +
                "name=" + name + '\'' +
                '}'
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}