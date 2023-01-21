package aris.thesis.theatricalplaysapi.entities

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Table(name = "users")
@Getter
@Setter
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Int? = null
    var email: String? = null
    var password: String? = null
    var enabled: Boolean? = null

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_authorities",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id")]
    )
    var authorities: MutableSet<Authority> = HashSet()
}