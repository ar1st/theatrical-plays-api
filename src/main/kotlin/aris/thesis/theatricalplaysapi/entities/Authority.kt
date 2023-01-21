package aris.thesis.theatricalplaysapi.entities

import lombok.Data
import javax.persistence.*

@Entity
@Table(name = "authorities")
@Data
class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    val id: Int? = null

    @Column(length = 20)
    val name: String? = null

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    val users: MutableSet<User> = HashSet()
}